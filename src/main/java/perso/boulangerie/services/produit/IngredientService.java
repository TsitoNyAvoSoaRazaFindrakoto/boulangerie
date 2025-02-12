package perso.boulangerie.services.produit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.models.produit.Ingredient;
import perso.boulangerie.repositories.produit.IngredientRepo;


@Service
public class IngredientService {
	@Autowired
	private IngredientRepo ingredientRepo;

	public List<Ingredient> getAllIngredients() {
		return ingredientRepo.findAll();
	}

	public Ingredient getIngredientById(Integer id) {
		return ingredientRepo.findById(id).orElseThrow(()-> new RuntimeException("Ingredient not found with id: "+id));
	}

	public Ingredient save(Ingredient ingredient) {
		return ingredientRepo.save(ingredient);
	}

	public void deleteIngredient(Integer id) {
		ingredientRepo.deleteById(id);
	}
}
