package perso.boulangerie.employe.controllers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import perso.boulangerie.employe.models.Employe;
import perso.boulangerie.employe.repos.SexeRepo;
import perso.boulangerie.employe.repos.TypeEmployeRepo;
import perso.boulangerie.employe.services.EmployeService;

@Controller
@RequestMapping("/employe")
@AllArgsConstructor
public class EmployeController {
	private final EmployeService employeService;
	private final TypeEmployeRepo typeEmployeRepo;
	private final SexeRepo sexeRepo;

	// Liste des employés
	@GetMapping
	public String listEmployes(Model model) {
		List<Employe> employes = employeService.findAllEmployes();
		model.addAttribute("employes", employes);
		return "employe/list"; // Vue pour afficher la liste
	}

	// Formulaire de création
	@GetMapping("/create")
	public String createForm(Model model) {
		model.addAttribute("employe", new Employe());
		model.addAttribute("typeEmployes", typeEmployeRepo.findAll());
		model.addAttribute("sexes", sexeRepo.findAll());
		return "employe/form"; // Vue pour le formulaire
	}

	// Enregistrement d'un nouvel employé
	@PostMapping("/create")
	public String createEmploye(@ModelAttribute Employe employe, RedirectAttributes redirectAttributes) {
		employe.setEstEmploye(true);
		employeService.createEmploye(employe);
		redirectAttributes.addFlashAttribute("success", "Employé créé avec succès !");
		return "redirect:/employe";
	}

	// Formulaire de modification
	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
		Employe employe = employeService.findEmployeById(id)
				.orElseThrow(() -> new RuntimeException("Employé introuvable avec l'ID " + id));
		model.addAttribute("employe", employe);
		model.addAttribute("typeEmployes", typeEmployeRepo.findAll());
		return "employe/form"; // Vue pour le formulaire
	}

	// Mise à jour d'un employé
	@PostMapping("/edit/{id}")
	public String updateEmploye(@PathVariable Integer id, @ModelAttribute Employe employe,
			RedirectAttributes redirectAttributes) {
		employeService.updateEmploye(id, employe);
		redirectAttributes.addFlashAttribute("success", "Employé mis à jour avec succès !");
		return "redirect:/employe";
	}

	// Suppression d'un employé
	@GetMapping("/delete/{id}")
	public String deleteEmploye(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
		employeService.deleteEmploye(id);
		redirectAttributes.addFlashAttribute("success", "Employé supprimé avec succès !");
		return "redirect:/employe";
	}

	// Liste des vendeurs
	@GetMapping("/vendeurs")
	public String listVendeurs(Model model) {
		List<Employe> vendeurs = employeService.findAllVendeurs();
		model.addAttribute("vendeurs", vendeurs);
		model.addAttribute("etatGenre", employeService.filterListBySexe(vendeurs));
		return "employe/vendeur-list"; // Vue pour afficher les vendeurs
	}

	// Recherche de vendeurs par critères
	@GetMapping("/search-vendeurs")
	public String searchVendeurs(@RequestParam(required = false) BigDecimal minCommission,
			@RequestParam(required = false) BigDecimal maxCommission, @RequestParam(required = false) LocalDate minPeriod,
			@RequestParam(required = false) LocalDate maxPeriod, Model model) {
		List<Employe> vendeurs = employeService.findAllVendeursByCriteria(minCommission, maxCommission, minPeriod,
				maxPeriod);
		model.addAttribute("vendeurs", vendeurs);
		model.addAttribute("etat-genre", employeService.filterListBySexe(vendeurs));
		return "employe/vendeur-list"; // Vue pour afficher les résultats
	}
}
