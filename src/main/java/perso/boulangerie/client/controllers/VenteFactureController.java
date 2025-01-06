package perso.boulangerie.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import perso.boulangerie.client.models.VenteFacture;
import perso.boulangerie.client.services.VenteFactureService;
import java.util.List;

@Controller
@RequestMapping("/venteFacture")
public class VenteFactureController {

	@Autowired
	private VenteFactureService venteFactureService;

	@GetMapping
	public String getAllVentes(Model model) {
		List<VenteFacture> venteFactures = venteFactureService.getVenteFactures();
		model.addAttribute("venteFactures", venteFactures);
		return "ventes/list";
	}

	@GetMapping("/{id}")
	public String getVenteById(@PathVariable Integer id, Model model) {
		VenteFacture venteFacture = venteFactureService.getVenteFacture(id);
		model.addAttribute("venteFacture", venteFacture);
		return "ventes/detail";
	}

	@GetMapping("/new")
	public String createVenteFactureForm(Model model) {
		model.addAttribute("venteFacture", new VenteFacture());
		return "ventes/form";
	}

	@PostMapping
	public String saveVenteFacture(@ModelAttribute VenteFacture venteFacture) {
		venteFactureService.save(venteFacture);
		return "redirect:/ventes";
	}

	@GetMapping("/edit/{id}")
	public String editVenteFactureForm(@PathVariable Integer id, Model model) {
		VenteFacture venteFacture = venteFactureService.getVenteFacture(id);
		model.addAttribute("venteFacture", venteFacture);
		return "ventes/form";
	}

	@PutMapping("/{id}")
	public String updateVenteFacture(@PathVariable Integer id, @ModelAttribute VenteFacture venteFacture) {
		venteFacture.setIdVenteFacture(id);
		venteFactureService.save(venteFacture);
		return "redirect:/ventes";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteVenteFacture(@PathVariable Integer id) {
		venteFactureService.deleteFactureVente(id);
		return "redirect:/ventes";
	}
}
