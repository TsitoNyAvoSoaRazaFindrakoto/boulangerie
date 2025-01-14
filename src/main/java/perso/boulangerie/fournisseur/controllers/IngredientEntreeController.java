package perso.boulangerie.fournisseur.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import perso.boulangerie.fournisseur.models.IngredientEntree;
import perso.boulangerie.fournisseur.services.IngredientEntreeService;

@Controller
@RequestMapping("/fournisseur/ingredient-entrees")
public class IngredientEntreeController {

	private IngredientEntreeService ingredientEntreeService;

	@GetMapping
	public String getAllIngredientEntrees(Model model) {
		model.addAttribute("ingredientEntrees", ingredientEntreeService.getIngredientEntrees());
		return "fournisseur/ingredient-entrees/list";
	}

	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("ingredientEntree", new IngredientEntree());
		return "fournisseur/ingredient-entrees/form";
	}

	@GetMapping("/{id}")
	public String getIngredientEntreeDetails(@PathVariable("id") Integer id, Model model) {
		IngredientEntree ingredientEntree = ingredientEntreeService.findIngredientEntree(id);
		model.addAttribute("ingredientEntree", ingredientEntree);
		return "fournisseur/ingredient-entrees/detail";
	}

	@PostMapping
	public String createIngredientEntree(@ModelAttribute IngredientEntree ingredientEntree) {
		ingredientEntreeService.save(ingredientEntree);
		return "redirect:/fournisseur/ingredient-entrees";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		IngredientEntree ingredientEntree = ingredientEntreeService.findIngredientEntree(id);
		model.addAttribute("ingredientEntree", ingredientEntree);
		return "fournisseur/ingredient-entrees/form";
	}

	@PostMapping("/{id}")
	public String updateIngredientEntree(@ModelAttribute IngredientEntree ingredientEntree) {
		ingredientEntreeService.save(ingredientEntree);
		return "redirect:/fournisseur/ingredient-entrees";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteIngredientEntree(@PathVariable("id") Integer id) {
		IngredientEntree ingredientEntree = ingredientEntreeService.findIngredientEntree(id);
		ingredientEntreeService.deleteIngredientEntree(ingredientEntree);
		return "redirect:/fournisseur/ingredient-entrees";
	}
}