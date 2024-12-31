package perso.boulangerie.production.services;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import perso.boulangerie.production.models.Production;
import perso.boulangerie.production.repos.ProductionRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductionService {
	@Autowired
	private ProductionRepo productionRepo;
	@Autowired
	private ProductionDetailsService productionDetailsService;

	public List<Production> getProductions() {
		return productionRepo.findAll();
	}

	public Production findProduction(Integer id) {
		Production p = productionRepo.findById(id).orElseThrow(() -> new RuntimeException("Production not found with id: " + id));
		p.setProductionDetails(productionDetailsService.getByProduction(p));
		return p;
	}

	@Transactional
	public Production save(Production production) {
		production.setProductionDetails(productionDetailsService.createForProduction(production, true));
		production.setIdProduction(productionRepo.save(production).getIdProduction());
		productionDetailsService.saveAll(production.getProductionDetails());

		return findProduction(production.getIdProduction());
	}

	public List<Production> getStockProduit(Integer idIngredient){
		return productionRepo.findStockByIngredient(idIngredient);
	}

	public List<Production> findByDate(LocalDateTime dateProd){
		return productionRepo.findByDateProduction(dateProd);
	}

	public void delete(Integer id) {
		productionRepo.deleteById(id);
	}
}
