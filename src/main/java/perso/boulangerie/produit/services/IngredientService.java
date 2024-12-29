package perso.boulangerie.produit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.produit.models.Ingredient;
import perso.boulangerie.produit.repos.IngredientRepo;

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
