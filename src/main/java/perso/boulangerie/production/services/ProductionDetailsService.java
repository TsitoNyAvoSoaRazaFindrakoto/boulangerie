package perso.boulangerie.production.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.production.models.Production;
import perso.boulangerie.production.models.ProductionDetails;
import perso.boulangerie.production.repos.ProductionDetailsRepo;

@Service
public class ProductionDetailsService {
	@Autowired
	private ProductionDetailsRepo productionDetailsRepo;

	public List<ProductionDetails> getProductions() {
		return productionDetailsRepo.findAll();
	}

	public ProductionDetails findProduction(Integer id) {
		return productionDetailsRepo.findById(id).orElseThrow(() -> new RuntimeException("Production not found with id: " + id));
	}

	public ProductionDetails save(ProductionDetails productionDetails) {
		return productionDetailsRepo.save(productionDetails);
	}

	public List<ProductionDetails> getByProduction(Production p){
		return productionDetailsRepo.findByProduction(p);
	}

	public void delete(Integer id) {
		productionDetailsRepo.deleteById(id);
	}
}
