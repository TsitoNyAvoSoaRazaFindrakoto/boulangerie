package perso.boulangerie.services.production;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import perso.boulangerie.models.production.Production;
import perso.boulangerie.models.produit.ProduitFormat;
import perso.boulangerie.repositories.production.ProductionRepo;
import perso.boulangerie.repositories.produit.ProduitFormatRepo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductionService {
	private ProductionRepo productionRepo;
	private ProductionDetailsService productionDetailsService;
	private ProduitFormatRepo produitFormatRepo;

	public ProductionService(ProductionRepo productionRepo, ProductionDetailsService productionDetailsService, ProduitFormatRepo produitFormatRepo) {
		this.productionRepo = productionRepo;
		this.productionDetailsService = productionDetailsService;
		this.produitFormatRepo = produitFormatRepo;
	}

	public List<Production> getProductions() {
		return productionRepo.findAll();
	}

	public Production findProduction(Integer id) {
		Production p = productionRepo.findById(id).orElseThrow(() -> new RuntimeException("Production not found with id: " + id));
		p.setProductionDetails(productionDetailsService.getByProduction(p));
		return p;
	}

	public HashMap<Integer,List<Production>> getStockGroupByProduitFormat(){
		HashMap<Integer,List<Production>> stock = new HashMap<Integer,List<Production>>();
		for (ProduitFormat produitFormat : produitFormatRepo.findAll()){
			stock.put(produitFormat.getIdProduitFormat(), getStockProduitFormat(produitFormat.getIdProduitFormat()));
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

	public List<Production> getStockProduitFormat(Integer idProduit){
		return productionRepo.findStockByProduitFormat(idProduit);
	}

	public List<Production> findByDate(LocalDateTime dateProd){
		return productionRepo.findByDateProduction(dateProd);
	}

	public void delete(Integer id) {
		productionRepo.deleteById(id);
	}
}
