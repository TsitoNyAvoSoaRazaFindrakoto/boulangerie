package perso.boulangerie.controllers.produit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import perso.boulangerie.models.produit.Ingredient;
import perso.boulangerie.services.produit.IngredientService;

import java.util.List;


@Controller
@RequestMapping("/produit/ingredient")
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;

	@GetMapping
	public String getAllIngredients(Model model) {
		List<Ingredient> ingredients = ingredientService.getAllIngredients();
		model.addAttribute("ingredients", ingredients);
		return "produit/ingredient/list";
	}

	@GetMapping("/{id}")
	public String getIngredientById(@PathVariable Integer id, Model model) {
		Ingredient ingredient = ingredientService.getIngredientById(id);
		model.addAttribute("ingredient", ingredient);
		return "produit/ingredient/detail";
	}

	@GetMapping("/new")
	public String createIngredientForm(Model model) {
		model.addAttribute("ingredient", new Ingredient());
		return "produit/ingredient/form";
	}

	@PostMapping
	public String saveIngredient(@ModelAttribute Ingredient ingredient) {
		ingredientService.save(ingredient);
		return "redirect:/produit/ingredient";
	}

	@GetMapping("/edit/{id}")
	public String editIngredientForm(@PathVariable Integer id, Model model) {
		Ingredient ingredient = ingredientService.getIngredientById(id);
		model.addAttribute("ingredient", ingredient);
		return "produit/ingredient/form";
	}

	@PostMapping("/update/{id}")
	public String updateIngredient(@ModelAttribute Ingredient ingredient) {
		ingredientService.save(ingredient);
		return "redirect:/produit/ingredient";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteIngredient(@PathVariable Integer id) {
		ingredientService.deleteIngredient(id);
		return "redirect:/produit/ingredient";
	}
}
