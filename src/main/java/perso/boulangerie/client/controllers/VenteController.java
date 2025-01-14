package perso.boulangerie.client.controllers;

import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import perso.boulangerie.client.models.Vente;
import perso.boulangerie.client.services.ClientService;
import perso.boulangerie.client.services.VenteService;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@AllArgsConstructor
@Controller
@RequestMapping("client/vente")
public class VenteController {

	private VenteService venteService;
	private ClientService clientService;

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
		return "client/vente/form";
	}

	@PostMapping
	public String createVente(@ModelAttribute Vente vente, HttpSession session) {
		session.setAttribute("vente", venteService.save(vente));
		return "redirect:/client/vente-facture/new";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable Integer id, Model model) {
		Vente vente = venteService.findVente(id);
		model.addAttribute("vente", vente);
		model.addAttribute("clients", clientService.getClients());
		return "client/vente/form";
	}

	@PostMapping("/update/{id}")
	public String updateVente(@ModelAttribute Vente vente) {
		venteService.save(vente);
		return "redirect:/client/vente";
	}

	@PostMapping("/validate")
	public String validateVente(@SessionAttribute Vente vente) {
		venteService.validerVente(vente.getIdVente());
		return "redirect:/client/vente";
	}

	@GetMapping("/delete/{id}")
	public String deleteVente(@PathVariable Integer id,HttpSession session) {
		venteService.deleteVente(id);
		session.removeAttribute("vente");
		return "redirect:/client/vente";
	}
}
