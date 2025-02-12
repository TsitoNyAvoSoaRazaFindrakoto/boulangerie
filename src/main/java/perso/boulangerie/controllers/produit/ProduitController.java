package perso.boulangerie.controllers.produit;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import perso.boulangerie.models.produit.Produit;
import perso.boulangerie.services.produit.ProduitService;

import java.util.List;

@Controller
@RequestMapping("/produit")
@AllArgsConstructor
public class ProduitController {
	private ProduitService produitService;

	@GetMapping
	public String getAllProduits(Model model) {
		List<Produit> produits = produitService.getProduits();
		model.addAttribute("produits", produits);
		return "produit/produit/list";
	}

	@GetMapping("/{id}")
	public String getProduitById(@PathVariable Integer id, Model model) {
		Produit produit = produitService.getProduitById(id);
		Hibernate.initialize(produit.getPrix());
		model.addAttribute("produit", produit);
		return "produit/produit/detail";
	}

	@GetMapping("/new")
	public String createProduitForm(Model model) {
		model.addAttribute("produit", new Produit());
		return "produit/produit/form";
	}

	@PostMapping
	public String saveProduit(@ModelAttribute Produit produit) {
		produitService.save(produit);
		return "redirect:/produit";
	}

	@GetMapping("/edit/{id}")
	public String editProduitForm(@PathVariable Integer id, Model model) {
		Produit produit = produitService.getProduitById(id);
		model.addAttribute("produit", produit);
		return "produit/produit/form";
	}

	@PostMapping("/{id}")
	public String updateProduit(@PathVariable Integer id, @ModelAttribute Produit produit) {
		produit.setIdProduit(id);
		produitService.save(produit);
		return "redirect:/produit";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduit(@PathVariable Integer id) {
		produitService.deleteProduit(id);
		return "redirect:/produit";
	}
}
