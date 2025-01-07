package perso.boulangerie.fournisseur.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import perso.boulangerie.fournisseur.models.IngredientsFournisseurs;
import perso.boulangerie.fournisseur.services.IngredientsFournisseursService;
import java.util.List;


@Controller
@RequestMapping("/ingredients-fournisseurs")
public class IngredientsFournisseursController {

	@Autowired
	private IngredientsFournisseursService ingredientsFournisseursService;

	@GetMapping
	public String getAllIngredientsFournisseurs(Model model) {
		List<IngredientsFournisseurs> ingredientsFournisseursList = ingredientsFournisseursService.getIngredientsFournisseurss();
		model.addAttribute("ingredientsFournisseursList", ingredientsFournisseursList);
		return "fournisseur/ingredients-fournisseurs/list";
	}

	@GetMapping("/new")
	public String showNewForm(Model model) {
		model.addAttribute("ingredientsFournisseurs", new IngredientsFournisseurs());
		return "fournisseur/ingredients-fournisseurs/form";
	}

	@PostMapping
	public String saveIngredientsFournisseurs(@ModelAttribute IngredientsFournisseurs ingredientsFournisseurs) {
		ingredientsFournisseursService.save(ingredientsFournisseurs);
		return "redirect:/ingredients-fournisseurs";
	}

	@GetMapping("/edit/{id-ingredient}/{id-fournisseur}")
	public String showEditForm(@PathVariable("id-ingredient") Integer idIngredient, @PathVariable("id-fournisseur") Integer idFournisseur, Model model) {
		IngredientsFournisseurs ingredientsFournisseurs = ingredientsFournisseursService.findIngredientsFournisseurs(idIngredient, idFournisseur);
		model.addAttribute("ingredientsFournisseurs", ingredientsFournisseurs);
		return "ingredientsFournisseurs/form";
	}

	@PutMapping("/update")
	public String updateIngredientsFournisseurs(@ModelAttribute IngredientsFournisseurs ingredientsFournisseurs) {
		ingredientsFournisseursService.save(ingredientsFournisseurs);
		return "redirect:/ingredientsFournisseurs";
	}

	@DeleteMapping("/delete/{id-ingredient}/{id-fournisseur}")
	public String deleteIngredientsFournisseurs(@PathVariable("id-ingredient") Integer idIngredient, @PathVariable("id-fournisseur") Integer idFournisseur) {
		IngredientsFournisseurs ingredientsFournisseurs = ingredientsFournisseursService.findIngredientsFournisseurs(idIngredient, idFournisseur);
		ingredientsFournisseursService.deleteIngredientsFournisseurs(ingredientsFournisseurs);
		return "redirect:/ingredientsFournisseurs";
	}
}
