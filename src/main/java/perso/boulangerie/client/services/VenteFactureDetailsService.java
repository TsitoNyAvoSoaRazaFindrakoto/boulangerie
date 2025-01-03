package perso.boulangerie.client.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.client.models.VenteFacture;
import perso.boulangerie.client.models.VenteFactureDetails;
import perso.boulangerie.client.repos.VenteFactureDetailsRepo;
import perso.boulangerie.production.models.Production;
import perso.boulangerie.production.services.ProductionService;

@Service
public class VenteFactureDetailsService {
	@Autowired private VenteFactureDetailsRepo venteFactureDetailsRepo;
	@Autowired private ProductionService productionService;

	private HashMap<Integer,List<Production>> stockProduit = productionService.getStockGroupByProduit();

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
		List<Production> productions = stockProduit.get(facture.getProduit().getIdProduit());

		if (productions == null || productions.isEmpty()) {
			throw new IllegalStateException("Not enough stock for product: " + facture.getProduit().getNom());
		}

		Production production = productions.get(0);
		while (necessesaryQuantity > 0) {
			if (productions.isEmpty() || production.getDateProduction().isAfter(facture.getVente().getDateVente())) {
				throw new IllegalStateException("Not enough stock for product: " + facture.getProduit().getNom());
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
}
