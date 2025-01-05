package perso.boulangerie.fournisseur.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import perso.boulangerie.fournisseur.models.IngredientEntree;
import perso.boulangerie.fournisseur.services.IngredientEntreeService;

@Controller
@RequestMapping("/ingredientEntrees")
public class IngredientEntreeController {

	@Autowired
	private IngredientEntreeService ingredientEntreeService;

	@GetMapping
	public String getAllIngredientEntrees(Model model) {
		model.addAttribute("ingredientEntrees", ingredientEntreeService.getIngredientEntrees());
		return "ingredientEntrees/list";
	}

	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("ingredientEntree", new IngredientEntree());
		return "ingredientEntrees/create";
	}

	@GetMapping("/{id}")
	public String getIngredientEntreeDetails(@PathVariable("id") Integer id, Model model) {
		IngredientEntree ingredientEntree = ingredientEntreeService.findIngredientEntree(id);
		model.addAttribute("ingredientEntree", ingredientEntree);
		return "ingredientEntrees/details";
	}

	@PostMapping
	public String createIngredientEntree(@ModelAttribute IngredientEntree ingredientEntree) {
		ingredientEntreeService.save(ingredientEntree);
		return "redirect:/ingredientEntrees";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		IngredientEntree ingredientEntree = ingredientEntreeService.findIngredientEntree(id);
		model.addAttribute("ingredientEntree", ingredientEntree);
		return "ingredientEntrees/update";
	}

	@PostMapping("/{id}")
	public String updateIngredientEntree(@ModelAttribute IngredientEntree ingredientEntree) {
		ingredientEntreeService.save(ingredientEntree);
		return "redirect:/ingredientEntrees";
	}

	@GetMapping("/delete/{id}")
	public String deleteIngredientEntree(@PathVariable("id") Integer id) {
		IngredientEntree ingredientEntree = ingredientEntreeService.findIngredientEntree(id);
		ingredientEntreeService.deleteIngredientEntree(ingredientEntree);
		return "redirect:/ingredientEntrees";
	}
}