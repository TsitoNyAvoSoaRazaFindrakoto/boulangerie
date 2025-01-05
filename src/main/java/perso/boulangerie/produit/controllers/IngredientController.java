package perso.boulangerie.produit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import perso.boulangerie.produit.models.Ingredient;
import perso.boulangerie.produit.services.IngredientService;
import java.util.List;


@Controller
@RequestMapping("/ingredients")
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;

	@GetMapping
	public String getAllIngredients(Model model) {
		List<Ingredient> ingredients = ingredientService.getAllIngredients();
		model.addAttribute("ingredients", ingredients);
		return "ingredients/list";
	}

	@GetMapping("/{id}")
	public String getIngredientById(@PathVariable Integer id, Model model) {
		Ingredient ingredient = ingredientService.getIngredientById(id);
		model.addAttribute("ingredient", ingredient);
		return "ingredients/detail";
	}

	@GetMapping("/new")
	public String createIngredientForm(Model model) {
		model.addAttribute("ingredient", new Ingredient());
		return "ingredients/form";
	}

	@PostMapping
	public String saveIngredient(@ModelAttribute Ingredient ingredient) {
		ingredientService.save(ingredient);
		return "redirect:/ingredients";
	}

	@GetMapping("/edit/{id}")
	public String editIngredientForm(@PathVariable Integer id, Model model) {
		Ingredient ingredient = ingredientService.getIngredientById(id);
		model.addAttribute("ingredient", ingredient);
		return "ingredients/form";
	}

	@PutMapping("/update/{id}")
	public String updateIngredient(@ModelAttribute Ingredient ingredient) {
		ingredientService.save(ingredient);
		return "redirect:/ingredients";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteIngredient(@PathVariable Integer id) {
		ingredientService.deleteIngredient(id);
		return "redirect:/ingredients";
	}
}
