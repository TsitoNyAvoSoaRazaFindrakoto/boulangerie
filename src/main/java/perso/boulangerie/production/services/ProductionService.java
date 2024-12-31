package perso.boulangerie.production.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import perso.boulangerie.production.models.Production;
import perso.boulangerie.production.repos.ProductionRepo;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductionService {
	@Autowired
	private ProductionRepo productionRepo;

	public List<Production> getProductions() {
		return productionRepo.findAll();
	}

	public Production findProduction(Integer id) {
		return productionRepo.findById(id).orElseThrow(() -> new RuntimeException("Production not found with id: " + id));
	}

	public Production save(Production production) {
		return productionRepo.save(production);
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
