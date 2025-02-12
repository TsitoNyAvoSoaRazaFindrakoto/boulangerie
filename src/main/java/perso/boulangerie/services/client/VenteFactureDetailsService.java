package perso.boulangerie.services.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import perso.boulangerie.models.client.VenteFacture;
import perso.boulangerie.models.client.VenteFactureDetails;
import perso.boulangerie.models.production.Production;
import perso.boulangerie.repositories.client.VenteFactureDetailsRepo;
import perso.boulangerie.services.production.ProductionService;

@Service
public class VenteFactureDetailsService {
	@Autowired private VenteFactureDetailsRepo venteFactureDetailsRepo;
	@Autowired private ProductionService productionService;

	private HashMap<Integer,List<Production>> stockProduit;

	public VenteFactureDetailsService(VenteFactureDetailsRepo venteFactureDetailsRepo,
			ProductionService productionService) {
		this.venteFactureDetailsRepo = venteFactureDetailsRepo;
		this.productionService = productionService;
		this.stockProduit = productionService.getStockGroupByProduitFormat();
	}

	public VenteFactureDetails save(VenteFactureDetails VenteDetails){
		return venteFactureDetailsRepo.save(VenteDetails);
	}

	public List<VenteFactureDetails> getVenteDetailss(){
		return venteFactureDetailsRepo.findAll();
	}

	public VenteFactureDetails findVenteDetails(Integer id){
		return venteFactureDetailsRepo.findById(id).orElseThrow(() -> new RuntimeException("VenteDetails not found with id: " + id));
	}
	
	public List<VenteFactureDetails> createForFacture(VenteFacture facture, boolean updateStock) {
		List<VenteFactureDetails> factureDetails = new ArrayList<>();

		Integer necessesaryQuantity = facture.getQuantite();
		List<Production> productions = stockProduit.get(facture.getProduitFormat().getIdProduitFormat());

		if (productions == null || productions.isEmpty()) {
			throw new IllegalStateException("Not enough stock for product: " + facture.getProduitFormat().getProduit().getNom() + "-" + facture.getProduitFormat().getFormat().getNom());
		}

		Production production = productions.get(0);
		while (necessesaryQuantity > 0) {
			if (productions.isEmpty() || production.getDateProduction().isAfter(facture.getVente().getDateVente())) {
				throw new IllegalStateException("Not enough stock for product: " + facture.getProduitFormat().getProduit().getNom() + "-" + facture.getProduitFormat().getFormat().getNom());
			}

			if (production.getQuantite() == 0) {
				productions.remove(0);
				if (!productions.isEmpty()) {
					production = productions.get(0);
				}
				continue;
			}

			Integer quantity = Math.min(production.getQuantite(), necessesaryQuantity);
			production.setQuantite(production.getQuantite() - quantity);
			necessesaryQuantity -= quantity;

			VenteFactureDetails vfd = new VenteFactureDetails();
			vfd.setProduction(production);
			vfd.setQuantite(quantity);

			factureDetails.add(vfd);

			if (updateStock) {
				productionService.save(production);
			}
		}

		return factureDetails;
	}

	public void saveAll(List<VenteFactureDetails> venteDetailsList) {
		venteFactureDetailsRepo.saveAll(venteDetailsList);
	}

	public List<VenteFactureDetails> findByFacture(VenteFacture facture){
		return venteFactureDetailsRepo.findByVenteFacture(facture);
	}

	public void deleteVenteDetails(VenteFactureDetails VenteDetails){
		venteFactureDetailsRepo.delete(VenteDetails);
	}

	@Transactional
	public void deleteByIdVenteFacture(Integer idVenteFacture){
		venteFactureDetailsRepo.deleteByIdVenteFacture(idVenteFacture);
	}

	@Transactional
	public void deleteByIdVente(Integer idVente){
		venteFactureDetailsRepo.deleteByIdVente(idVente);
	}
}
