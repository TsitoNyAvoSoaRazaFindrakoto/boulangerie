package perso.boulangerie.fournisseur.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import perso.boulangerie.fournisseur.models.Fournisseur;
import perso.boulangerie.fournisseur.services.FournisseurService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/fournisseur")
public class FournisseurController {

	@Autowired
	private FournisseurService fournisseurService;

	@GetMapping
	public String getAllFournisseurs(Model model) {
		List<Fournisseur> fournisseur = fournisseurService.getFournisseurs();
		model.addAttribute("fournisseur", fournisseur);
		return "fournisseur/fournisseur/list";
	}

	@GetMapping("/{id}")
	public String getFournisseurById(@PathVariable Integer id, Model model) {
		Fournisseur fournisseur = fournisseurService.findFournisseur(id);
		model.addAttribute("fournisseur", fournisseur);
		return "fournisseur/fournisseur/detail";
	}

	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("fournisseur", new Fournisseur());
		return "fournisseur/fournisseur/form";
	}

	@PostMapping
	public String createFournisseur(@ModelAttribute Fournisseur fournisseur) {
		fournisseurService.save(fournisseur);
		return "redirect:/fournisseur";
	}

	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Integer id, Model model) {
		Fournisseur fournisseur = fournisseurService.findFournisseur(id);
		model.addAttribute("fournisseur", fournisseur);
		return "fournisseur/fournisseur/form";
	}

	@PutMapping("/update/{id}")
	public String updateFournisseur(@ModelAttribute Fournisseur fournisseurDetails) {
		fournisseurService.save(fournisseurDetails);
		return "redirect:/fournisseur";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteFournisseur(@PathVariable Integer id) {
		Fournisseur fournisseur = fournisseurService.findFournisseur(id);
		fournisseurService.deleteFournisseur(fournisseur);
		return "redirect:/fournisseur";
	}
}
