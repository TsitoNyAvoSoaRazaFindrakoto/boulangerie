package perso.boulangerie.fournisseur.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import perso.boulangerie.fournisseur.models.IngredientsFournisseurs;
import perso.boulangerie.fournisseur.services.FournisseurService;
import perso.boulangerie.fournisseur.services.IngredientsFournisseursService;
import perso.boulangerie.produit.services.IngredientService;

import java.util.List;

@Controller
@RequestMapping("/fournisseur/ingredients-fournisseurs")
public class IngredientsFournisseursController {

	private final IngredientsFournisseursService ingredientsFournisseursService;
	private final FournisseurService fournisseurService;
	private final IngredientService ingredientService;

	public IngredientsFournisseursController(IngredientsFournisseursService ingredientsFournisseursService,
			FournisseurService fournisseurService, IngredientService ingredientService) {
		this.ingredientsFournisseursService = ingredientsFournisseursService;
		this.fournisseurService = fournisseurService;
		this.ingredientService = ingredientService;
	}

	@GetMapping
	public String getAllIngredientsFournisseurs(Model model) {
		List<IngredientsFournisseurs> ingredientsFournisseursList = ingredientsFournisseursService
				.getIngredientsFournisseurss();
		model.addAttribute("ingredientsFournisseursList", ingredientsFournisseursList);
		return "fournisseur/ingredients-fournisseurs/list";
	}

	@GetMapping("new/{fournisseur-id}")
	public String showNewForm(@PathVariable("fournisseur-id") Integer fournisseurId, Model model) {
		model.addAttribute("ingredientsFournisseurs", new IngredientsFournisseurs());
		model.addAttribute("fournisseur", fournisseurService.findFournisseur(fournisseurId));
		model.addAttribute("ingredients", ingredientService.getAllIngredients());
		return "fournisseur/ingredients-fournisseurs/form";
	}

	@PostMapping
	public String saveIngredientsFournisseurs(@ModelAttribute IngredientsFournisseurs ingredientsFournisseurs) {
		ingredientsFournisseursService.save(ingredientsFournisseurs);
		return "redirect:/fournisseur/" + ingredientsFournisseurs.getFournisseur().getIdFournisseur();
	}

	@GetMapping("/edit/{id-ingredient}/{id-fournisseur}")
	public String showEditForm(@PathVariable("id-ingredient") Integer idIngredient,
			@PathVariable("id-fournisseur") Integer idFournisseur, Model model) {
		IngredientsFournisseurs ingredientsFournisseurs = ingredientsFournisseursService
				.findIngredientsFournisseurs(idIngredient, idFournisseur);
		model.addAttribute("ingredientsFournisseurs", ingredientsFournisseurs);
		return "fournisseur/ingredients-fournisseurs/form";
	}

	@PostMapping("/update")
	public String updateIngredientsFournisseurs(@ModelAttribute IngredientsFournisseurs ingredientsFournisseurs) {
		ingredientsFournisseursService.save(ingredientsFournisseurs);
		return "redirect:/fournisseur/ingredients-fournisseurs";
	}

	@DeleteMapping("/delete/{id-ingredient}/{id-fournisseur}")
	public String deleteIngredientsFournisseurs(@PathVariable("id-ingredient") Integer idIngredient,
			@PathVariable("id-fournisseur") Integer idFournisseur) {
		IngredientsFournisseurs ingredientsFournisseurs = ingredientsFournisseursService
				.findIngredientsFournisseurs(idIngredient, idFournisseur);
		ingredientsFournisseursService.deleteIngredientsFournisseurs(ingredientsFournisseurs);
		return "redirect:/fournisseur/ingredients-fournisseurs";
	}
}
