package perso.boulangerie.produit.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import perso.boulangerie.produit.models.ProduitConseil;
import perso.boulangerie.produit.models.ProduitFormat;
import perso.boulangerie.produit.repos.FormatRepo;
import perso.boulangerie.produit.repos.ProduitRepo;
import perso.boulangerie.produit.services.ProduitConseilService;
import perso.boulangerie.produit.services.ProduitFormatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;



@AllArgsConstructor
@Controller
@RequestMapping("/produit/produit-format")
public class ProduitFormatController {
	private final ProduitFormatService produitFormatService;
	private final ProduitConseilService produitConseilService;
	private final FormatRepo formatRepo;
	private final ProduitRepo produitRepo;

	@GetMapping("/conseils")
	public String getConseils(@RequestParam(required = false,name = "mois") Integer mois,@RequestParam(required = false,name = "annee") Integer annee,Model model) {
		List<ProduitConseil> produits = produitConseilService.getForYearMonth(mois,annee);
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

	public String toForm(Model model) {
		model.addAttribute("formats",formatRepo.findAll());
		model.addAttribute("produits",produitRepo.findAll());
		return "produit/produit-format/form";
	}

	@GetMapping("/create")
	public String toCreateForm(Model model){
		model.addAttribute("produitFormat", new ProduitFormat());
		return toForm(model);
	}

	@GetMapping("/edit/{id}")
	public String toCreateForm(Model model,@PathVariable Integer id){
		model.addAttribute("produitFormat", produitFormatService.getProduitFormat(id));
		return toForm(model);
	}


	
	
	
	
}
