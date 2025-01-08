package perso.boulangerie.client.controllers;

import org.springframework.web.bind.annotation.*;
import perso.boulangerie.client.models.Vente;
import perso.boulangerie.client.models.VenteFacture;
import perso.boulangerie.client.services.ClientService;
import perso.boulangerie.client.services.VenteService;
import perso.boulangerie.produit.services.ProduitFormatService;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("client/vente")
public class VenteController {

	private VenteService venteService;
	private ClientService clientService;
	private ProduitFormatService produitFormatService;

	public VenteController(VenteService venteService, ClientService clientService,
			ProduitFormatService produitFormatService) {
		this.venteService = venteService;
		this.clientService = clientService;
		this.produitFormatService = produitFormatService;
	}

	@GetMapping
	public String getAllVentes(Model model) {
		List<Vente> ventes = venteService.getVentes();
		model.addAttribute("ventes", ventes);
		return "client/vente/list";
	}

	@GetMapping("/{id}")
	public String getVenteById(@PathVariable Integer id, Model model) {
		Vente vente = venteService.findVente(id);
		model.addAttribute("vente", vente);
		return "client/vente/detail";
	}

	@GetMapping("/new")
	public String showCreateForm(Model model) {
		model.addAttribute("vente", new Vente());
		model.addAttribute("clients", clientService.getClients());
		model.addAttribute("produit_formats",produitFormatService.getProduitFormats());
		return "client/vente/form";
	}

	@PostMapping
	public String createVente(@ModelAttribute Vente vente, @RequestParam List<VenteFacture> venteDetails) {
		venteService.saveWithDetails(vente, venteDetails);
		return "redirect:/client/vente";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable Integer id, Model model) {
		Vente vente = venteService.findVente(id);
		model.addAttribute("vente", vente);
		model.addAttribute("clients", clientService.getClients());
		return "client/vente/form";
	}

	@PutMapping("/update/{id}")
	public String updateVente(@ModelAttribute Vente vente, @RequestParam List<VenteFacture> venteDetails) {
		venteService.saveWithDetails(vente, venteDetails);
		return "redirect:/client/vente";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteVente(@PathVariable Integer id) {
		venteService.deleteVente(id);
		return "redirect:/client/vente";
	}
}
