package perso.boulangerie.services.produit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import perso.boulangerie.models.produit.Ingredient;
import perso.boulangerie.models.produit.Produit;
import perso.boulangerie.models.produit.ProduitFormat;
import perso.boulangerie.models.produit.ProduitFormatRecette;
import perso.boulangerie.models.produit.ProduitsRecettes;
import perso.boulangerie.models.produit.Recette;
import perso.boulangerie.models.produit.id.ProduitFormatRecetteId;
import perso.boulangerie.models.produit.id.ProduitsRecettesId;
import perso.boulangerie.repositories.produit.ProduitFormatsRecettesRepo;
import perso.boulangerie.repositories.produit.ProduitsRecettesRepo;

@Service
@AllArgsConstructor
public class RecettesService {
	private ProduitsRecettesRepo produitsRecettesRepo;
	private ProduitFormatsRecettesRepo produitFormatsRecettesRepo;

	public List<ProduitsRecettes> getAllProduits() {
		return produitsRecettesRepo.findAll();
	}

	public ProduitsRecettes getProduitById(ProduitsRecettesId id) {
		return produitsRecettesRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("ProduitsRecettes not found with ingredient-id: " + id.getIngredient()
						+ " and produit-id: " + id.getProduit()));
	}

	public ProduitsRecettes save(ProduitsRecettes ProduitsRecettes) {
		return produitsRecettesRepo.save(ProduitsRecettes);
	}

	public List<ProduitsRecettes> findByProduit(Produit produit) {
		return produitsRecettesRepo.findByProduit(produit);
	}

	public List<ProduitsRecettes> findByIngredient(Ingredient ingredient) {
		return produitsRecettesRepo.findByIngredient(ingredient);
	}

	public void deleteProduit(ProduitsRecettesId id) {
		produitsRecettesRepo.deleteById(id);
	}

	public List<ProduitFormatRecette> getAllProduitFormatRecettes() {
		return produitFormatsRecettesRepo.findAll();
	}

	public ProduitFormatRecette getProduitFormatRecetteById(ProduitFormatRecetteId id) {
		return produitFormatsRecettesRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("ProduitFormatRecette not found with produitFormat-id: "
						+ id.getProduitFormat() + " and ingredient-id: " + id.getIngredient()));
	}

	public ProduitFormatRecette saveProduitFormatRecette(ProduitFormatRecette ProduitFormatRecette) {
		return produitFormatsRecettesRepo.save(ProduitFormatRecette);
	}

	public List<ProduitFormatRecette> findByProduitFormat(ProduitFormat produitFormat) {
		return produitFormatsRecettesRepo.findByProduitFormat(produitFormat);
	}

	public List<perso.boulangerie.models.produit.ProduitFormatRecette> findByIngredientProduitFormat(
			Ingredient ingredient) {
		return produitFormatsRecettesRepo.findByIngredient(ingredient);
	}

	public void deleteProduitFormatRecette(perso.boulangerie.models.produit.id.ProduitFormatRecetteId id) {
		produitFormatsRecettesRepo.deleteById(id);
	}

	public List<Recette> findRecetteByProduitFormat(ProduitFormat produitFormat) {
		List<Recette> ingredients = new ArrayList<>();
		ingredients.addAll(findByProduit(produitFormat.getProduit()));
		ingredients.addAll(findByProduitFormat(produitFormat));
		return ingredients;
	}
}
