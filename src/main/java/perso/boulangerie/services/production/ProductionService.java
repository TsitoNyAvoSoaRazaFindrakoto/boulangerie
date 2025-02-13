package perso.boulangerie.services.production;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import perso.boulangerie.models.production.Production;
import perso.boulangerie.models.produit.ProduitFormat;
import perso.boulangerie.repositories.production.ProductionRepo;
import perso.boulangerie.repositories.produit.ProduitFormatRepo;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductionService {
	private ProductionRepo productionRepo;
	private ProductionDetailsService productionDetailsService;
	private ProduitFormatRepo produitFormatRepo;

	public List<Production> getProductions() {
		return productionRepo.findAll();
	}

	public Production findProduction(Integer id) {
		return productionRepo.findByIdWithProductionDetails(id);
	}

	public HashMap<Integer, List<Production>> getStockGroupByProduitFormat() {
		HashMap<Integer, List<Production>> stock = new HashMap<Integer, List<Production>>();
		for (ProduitFormat produitFormat : produitFormatRepo.findAll()) {
			stock.put(produitFormat.getIdProduitFormat(), getStockProduitFormat(produitFormat.getIdProduitFormat()));
		}
		return stock;
	}

	@Transactional
	public Production save(Production production) {
		production.setProduitFormat(produitFormatRepo.findById(production.getProduitFormat().getIdProduitFormat()).get());
		production.setProductionDetails(productionDetailsService.createForProduction(production, true));
		return productionRepo.save(production);
	}

	public List<Production> getStockProduitFormat(Integer idProduit) {
		return productionRepo.findStockByProduitFormat(idProduit);
	}

	public List<Production> findByDate(LocalDateTime dateProd) {
		return productionRepo.findByDateProduction(dateProd);
	}

	public void delete(Integer id) {
		productionRepo.deleteById(id);
	}
}
