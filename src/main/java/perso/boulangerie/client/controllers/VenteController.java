package perso.boulangerie.client.controllers;

import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import perso.boulangerie.client.models.Vente;
import perso.boulangerie.client.services.ClientService;
import perso.boulangerie.client.services.VenteService;
import perso.boulangerie.employe.repos.EmployeRepo;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;


@AllArgsConstructor
@Controller
@RequestMapping("client/vente")
public class VenteController {

	private VenteService venteService;
	private ClientService clientService;
	private EmployeRepo employeRepo;

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
		model.addAttribute("employes", employeRepo.findAllVendeurs());
		return "client/vente/form";
	}

	@PostMapping
	public String createVente(@ModelAttribute Vente vente, HttpSession session) {
		Vente newVente = venteService.save(vente);
		session.setAttribute("vente", newVente);
		return "redirect:/client/vente/"+newVente.getIdVente();
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

	@PostMapping("/validate/{id}")
	public String validateVente(@PathVariable Integer id) {
		venteService.validerVente(id);
		return "redirect:/client/vente";
	}

	@PostMapping("/discard/{id}")
	public String discardVente(@PathVariable Integer id) {
		venteService.deleteVente(id);
		return "redirect:/client/vente";
	}

	@GetMapping("/delete/{id}")
	public String deleteVente(@PathVariable Integer id,HttpSession session) {
		venteService.deleteVente(id);
		session.removeAttribute("vente");
		return "redirect:/client/vente";
	}
}