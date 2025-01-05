package perso.boulangerie.fournisseur.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import perso.boulangerie.fournisseur.models.Fournisseur;
import perso.boulangerie.fournisseur.services.FournisseurService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/fournisseurs")
public class FournisseurController {

	@Autowired
	private FournisseurService fournisseurService;

	@GetMapping
	public String getAllFournisseurs(Model model) {
		List<Fournisseur> fournisseurs = fournisseurService.getFournisseurs();
		model.addAttribute("fournisseurs", fournisseurs);
		return "fournisseurs/list";
	}

	@GetMapping("/{id}")
	public String getFournisseurById(@PathVariable Integer id, Model model) {
		Fournisseur fournisseur = fournisseurService.findFournisseur(id);
		model.addAttribute("fournisseur", fournisseur);
		return "fournisseurs/view";
	}

	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("fournisseur", new Fournisseur());
		return "fournisseurs/create";
	}

	@PostMapping
	public String createFournisseur(@ModelAttribute Fournisseur fournisseur) {
		fournisseurService.save(fournisseur);
		return "redirect:/fournisseurs";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Integer id, Model model) {
		Fournisseur fournisseur = fournisseurService.findFournisseur(id);
		model.addAttribute("fournisseur", fournisseur);
		return "fournisseurs/edit";
	}

	@PostMapping("/update/{id}")
	public String updateFournisseur(@ModelAttribute Fournisseur fournisseurDetails) {
		fournisseurService.save(fournisseurDetails);
		return "redirect:/fournisseurs";
	}

	@GetMapping("/delete/{id}")
	public String deleteFournisseur(@PathVariable Integer id) {
		Fournisseur fournisseur = fournisseurService.findFournisseur(id);
		fournisseurService.deleteFournisseur(fournisseur);
		return "redirect:/fournisseurs";
	}
}
