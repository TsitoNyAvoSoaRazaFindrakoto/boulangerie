package perso.boulangerie.production.services;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import perso.boulangerie.production.models.Production;
import perso.boulangerie.production.repos.ProductionRepo;
import perso.boulangerie.produit.models.Produit;
import perso.boulangerie.produit.repos.ProduitRepo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductionService {
	@Autowired
	private ProductionRepo productionRepo;
	@Autowired
	private ProductionDetailsService productionDetailsService;
	@Autowired
	private ProduitRepo produitRepo;

	public List<Production> getProductions() {
		return productionRepo.findAll();
	}

	public Production findProduction(Integer id) {
		Production p = productionRepo.findById(id).orElseThrow(() -> new RuntimeException("Production not found with id: " + id));
		p.setProductionDetails(productionDetailsService.getByProduction(p));
		return p;
	}

	public HashMap<Integer,List<Production>> getStockGroupByProduit(){
		HashMap<Integer,List<Production>> stock = new HashMap<Integer,List<Production>>();
		for (Produit Produit : produitRepo.findAll()){
			stock.put(Produit.getIdProduit(), productionRepo.findStockByProduit(Produit.getIdProduit()));
		}
		return stock;
	}

	@Transactional
	public Production save(Production production) {
		production.setProductionDetails(productionDetailsService.createForProduction(production, true));
		production.setIdProduction(productionRepo.save(production).getIdProduction());
		productionDetailsService.saveAll(production.getProductionDetails());

		return production;
	}

	public List<Production> getStockProduit(Integer idProduit){
		return productionRepo.findStockByProduit(idProduit);
	}

	public List<Production> findByDate(LocalDateTime dateProd){
		return productionRepo.findByDateProduction(dateProd);
	}

	public void delete(Integer id) {
		productionRepo.deleteById(id);
	}
}
