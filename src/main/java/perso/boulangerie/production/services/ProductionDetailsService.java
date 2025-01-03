package perso.boulangerie.production.services;

import java.util.HashMap;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.fournisseur.models.IngredientEntree;
import perso.boulangerie.fournisseur.services.IngredientEntreeService;
import perso.boulangerie.production.models.Production;
import perso.boulangerie.production.models.ProductionDetails;
import perso.boulangerie.production.repos.ProductionDetailsRepo;
import perso.boulangerie.produit.models.Produit;
import perso.boulangerie.produit.models.ProduitsRecettes;
import perso.boulangerie.produit.services.ProduitService;

@Service
public class ProductionDetailsService {
	@Autowired
	private ProductionDetailsRepo productionDetailsRepo;
	@Autowired
	private IngredientEntreeService ingredientEntreeService;
	@Autowired
	private ProduitService produitService;

	HashMap<Integer, List<IngredientEntree>> stockIngredient = ingredientEntreeService.getStockGroupByIngrdient();

	public List<ProductionDetails> getProductions() {
		return productionDetailsRepo.findAll();
	}

	public ProductionDetails findProduction(Integer id) {
		return productionDetailsRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Production not found with id: " + id));
	}


	public ProductionDetails save(ProductionDetails productionDetails) {
		return productionDetailsRepo.save(productionDetails);
	}

	public List<ProductionDetails> saveAll(List<ProductionDetails> productionDetailsList) {
		return productionDetailsRepo.saveAll(productionDetailsList);
	}

	public List<ProductionDetails> getByProduction(Production p) {
		return productionDetailsRepo.findByProduction(p);
	}

	public List<ProductionDetails> createForRecette(Production p, ProduitsRecettes recette) {

		List<ProductionDetails> productionDetails = new ArrayList<ProductionDetails>();

		BigDecimal necessesaryQuantity = recette.getQuantite().multiply(new BigDecimal(p.getQuantite()));

		IngredientEntree ingredientEntree = stockIngredient.get(recette.getIngredient().getIdIngredient()).get(0);
		while (necessesaryQuantity.compareTo(BigDecimal.ZERO) > 0) {
			if (stockIngredient.get(recette.getIngredient().getIdIngredient()).isEmpty()
					|| ingredientEntree.getDateEntree().isAfter(p.getDateProduction())) {
				throw new IllegalStateException(
						"Not enough of (" + recette.getIngredient().getNom() + ") to process the required quantity.");
			}

			if (ingredientEntree.getQuantite().compareTo(BigDecimal.ZERO) == 0) {
				stockIngredient.get(recette.getIngredient().getIdIngredient()).remove(0);
				ingredientEntree = stockIngredient.get(recette.getIngredient().getIdIngredient()).get(0);
				continue;
			}

			BigDecimal quantity = ingredientEntree.getQuantite().min(necessesaryQuantity);
			ingredientEntree.setQuantite(ingredientEntree.getQuantite().subtract(quantity));
			necessesaryQuantity = necessesaryQuantity.subtract(quantity);

			ProductionDetails pd = new ProductionDetails();
			pd.setProduction(p);
			pd.setIngredientEntree(ingredientEntree);
			pd.setQuantite(quantity);

			productionDetails.add(pd);
		}
		return productionDetails;
	}

	public List<ProductionDetails> createForProduction(Production p, boolean updateStock) {
		if (updateStock)
			stockIngredient = ingredientEntreeService.getStockGroupByIngrdient();

		Produit produit = produitService.getProduitById(p.getProduit().getIdProduit());
		List<ProductionDetails> productionDetails = new ArrayList<ProductionDetails>();

		for (ProduitsRecettes recette : produit.getRecettes()) {
			productionDetails.addAll(createForRecette(p, recette));
		}

		return productionDetails;
	}

	public void delete(Integer id) {
		productionDetailsRepo.deleteById(id);
	}
}
