package perso.boulangerie.produit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import perso.boulangerie.produit.models.Produit;
import perso.boulangerie.produit.services.ProduitService;

import java.util.List;

@Controller
@RequestMapping("/produits")
public class ProduitController {
	@Autowired
	private ProduitService produitService;

	@GetMapping
	public String getAllProduits(Model model) {
		List<Produit> produits = produitService.getAllProduits();
		model.addAttribute("produits", produits);
		return "produits/list";
	}

	@GetMapping("/{id}")
	public String getProduitById(@PathVariable Integer id, Model model) {
		Produit produit = produitService.getProduitById(id);
		model.addAttribute("produit", produit);
		return "produits/detail";
	}

	@GetMapping("/new")
	public String createProduitForm(Model model) {
		model.addAttribute("produit", new Produit());
		return "produits/form";
	}

	@PostMapping
	public String saveProduit(@ModelAttribute Produit produit) {
		produitService.save(produit);
		return "redirect:/produits";
	}

	@GetMapping("/edit/{id}")
	public String editProduitForm(@PathVariable Integer id, Model model) {
		Produit produit = produitService.getProduitById(id);
		model.addAttribute("produit", produit);
		return "produits/form";
	}

	@PostMapping("/{id}")
	public String updateProduit(@PathVariable Integer id, @ModelAttribute Produit produit) {
		produit.setIdProduit(id);
		produitService.save(produit);
		return "redirect:/produits";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduit(@PathVariable Integer id) {
		produitService.deleteProduit(id);
		return "redirect:/produits";
	}
}
