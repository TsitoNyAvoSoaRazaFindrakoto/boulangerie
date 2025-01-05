package perso.boulangerie.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import perso.boulangerie.client.models.Vente;
import perso.boulangerie.client.models.VenteFacture;
import perso.boulangerie.client.services.ClientService;
import perso.boulangerie.client.services.VenteService;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/ventes")
public class VenteController {

	@Autowired
	private VenteService venteService;
	@Autowired
	private ClientService clientService;

	@GetMapping
	public String getAllVentes(Model model) {
		List<Vente> ventes = venteService.getVentes();
		model.addAttribute("ventes", ventes);
		return "ventes/list";
	}

	@GetMapping("/{id}")
	public String getVenteById(@PathVariable Integer id, Model model) {
		Vente vente = venteService.findVente(id);
		model.addAttribute("vente", vente);
		return "ventes/view";
	}

	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("vente", new Vente());
		model.addAttribute("clients", clientService.getClients());
		return "ventes/form";
	}

	@PostMapping
	public String createVente(@ModelAttribute Vente vente, @RequestParam List<VenteFacture> venteDetails) {
		venteService.saveWithDetails(vente, venteDetails);
		return "redirect:/ventes";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable Integer id, Model model) {
		Vente vente = venteService.findVente(id);
		model.addAttribute("vente", vente);
		model.addAttribute("clients", clientService.getClients());
		return "ventes/form";
	}

	@PostMapping("/update/{id}")
	public String updateVente(@ModelAttribute Vente vente, @RequestParam List<VenteFacture> venteDetails) {
		venteService.saveWithDetails(vente, venteDetails);
		return "redirect:/ventes";
	}

	@GetMapping("/delete/{id}")
	public String deleteVente(@PathVariable Integer id) {
		venteService.deleteVente(id);
		return "redirect:/ventes";
	}
}
