package perso.boulangerie.fournisseur.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import perso.boulangerie.fournisseur.id.IngredientsFournisseursId;
import perso.boulangerie.fournisseur.models.IngredientEntree;
import perso.boulangerie.fournisseur.models.IngredientsFournisseurs;
import perso.boulangerie.fournisseur.repos.IngredientsFournisseursRepo;
import perso.boulangerie.fournisseur.services.IngredientEntreeService;

@Controller
@RequestMapping("/fournisseur/ingredient-entrees")
@AllArgsConstructor
public class IngredientEntreeController {

	private IngredientEntreeService ingredientEntreeService;
	private IngredientsFournisseursRepo ingredientsFournisseursRepo;

	@GetMapping
	public String getAllIngredientEntrees(Model model) {
		model.addAttribute("ingredientEntrees", ingredientEntreeService.getIngredientEntrees());
		return "fournisseur/ingredient-entrees/list";
	}

	@GetMapping("/{id-fournisseur}/new/{id-ingredient}")
	public String showCreateForm(Model model, @PathVariable("id-fournisseur") Integer idFournisseur,
			@PathVariable("id-ingredient") Integer idIngredient) {
		// Fetch the IngredientsFournisseurs entity
		IngredientsFournisseurs ingredientFournisseur = ingredientsFournisseursRepo
				.findById(new IngredientsFournisseursId(idFournisseur, idIngredient))
				.orElseThrow(() -> new RuntimeException("IngredientFournisseur not found"));

		// Create a new IngredientEntree and pre-populate it
		IngredientEntree ingredientEntree = new IngredientEntree();
		ingredientEntree.setFournisseur(ingredientFournisseur.getFournisseur());
		ingredientEntree.setIngredient(ingredientFournisseur.getIngredient());
		ingredientEntree.setPrixUnitaire(ingredientFournisseur.getPrixUnitaire());

		// Add to model
		model.addAttribute("ingredientEntree", ingredientEntree);
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

	@GetMapping("/delete/{id}")
	public String deleteIngredientEntree(@PathVariable("id") Integer id) {
		IngredientEntree ingredientEntree = ingredientEntreeService.findIngredientEntree(id);
		ingredientEntreeService.deleteIngredientEntree(ingredientEntree);
		return "redirect:/fournisseur/ingredient-entrees";
	}
}