package perso.boulangerie.produit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import perso.boulangerie.produit.id.ProduitsRecettesId;
import perso.boulangerie.produit.models.ProduitsRecettes;
import perso.boulangerie.produit.services.ProduitsRecettesService;

import java.util.List;

@Controller
@RequestMapping("/produits-recettes")
public class ProduitsRecettesController {

	@Autowired
	private ProduitsRecettesService produitsRecettesService;

	@GetMapping
	public String getAllProduits(Model model) {
		List<ProduitsRecettes> produits = produitsRecettesService.getAllProduits();
		model.addAttribute("produits", produits);
		return "produits-recettes/list";
	}

	@GetMapping("/{ingredientId}/{produitId}")
	public String getProduitById(@PathVariable Integer ingredientId, @PathVariable Integer produitId, Model model) {
		ProduitsRecettesId id = new ProduitsRecettesId(ingredientId, produitId);
		ProduitsRecettes produit = produitsRecettesService.getProduitById(id);
		model.addAttribute("produit", produit);
		return "produits-recettes/detail";
	}

	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("produit", new ProduitsRecettes());
		return "produits-recettes/form";
	}

	@PostMapping
	public String createProduit(@ModelAttribute ProduitsRecettes produitsRecettes) {
		produitsRecettesService.save(produitsRecettes);
		return "redirect:/produits-recettes";
	}

	@GetMapping("/{ingredientId}/{produitId}/edit")
	public String showUpdateForm(@PathVariable Integer ingredientId, @PathVariable Integer produitId, Model model) {
		ProduitsRecettesId id = new ProduitsRecettesId(ingredientId, produitId);
		ProduitsRecettes produit = produitsRecettesService.getProduitById(id);
		model.addAttribute("produit", produit);
		return "produits-recettes/form";
	}

	@PutMapping("/{ingredientId}/{produitId}")
	public String updateProduit(@ModelAttribute ProduitsRecettes produitsRecettes) {
		produitsRecettesService.save(produitsRecettes);
		return "redirect:/produits-recettes";
	}

	@DeleteMapping("/{ingredientId}/{produitId}")
	public String deleteProduit(@PathVariable Integer ingredientId, @PathVariable Integer produitId) {
		ProduitsRecettesId id = new ProduitsRecettesId(ingredientId, produitId);
		produitsRecettesService.deleteProduit(id);
		return "redirect:/produits-recettes";
	}
}
