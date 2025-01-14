package perso.boulangerie.produit.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import perso.boulangerie.produit.models.ProduitConseil;
import perso.boulangerie.produit.services.ProduitConseilService;
import perso.boulangerie.produit.services.ProduitFormatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@AllArgsConstructor
@Controller
@RequestMapping("/produit/produit-format")
public class ProduitFormatController {
	private final ProduitFormatService produitFormatService;
	private final ProduitConseilService produitConseilService;

	@GetMapping("/conseils")
	public String getConseils(@RequestParam(required = false,name = "moisAnnee") String moisAnnee,Model model) {
		List<ProduitConseil> produits = produitConseilService.getForYearMonth(moisAnnee);
		model.addAttribute("produits", produits);
		return "produit/produit-format/conseil-list";
	}

	@GetMapping("/conseils/new")
	public String newConseil(Model model) {
		model.addAttribute("produits", produitFormatService.getProduitFormats());
		model.addAttribute("produitConseil", new ProduitConseil());
		return "produit/produit-format/conseil-form";
	}

	@PostMapping("/conseils")
	public String postMethodName(@ModelAttribute ProduitConseil produitConseil) {
		produitConseilService.save(produitConseil);
		return "redirect:/produit/produit-format/conseils";
	}
	
	
}
