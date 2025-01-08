package perso.boulangerie.production.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import perso.boulangerie.production.models.Production;
import perso.boulangerie.production.services.ProductionService;
import java.util.List;


@Controller
@RequestMapping("/production")
public class ProductionController {

	@Autowired
	private ProductionService productionService;

	@GetMapping
	public String getAllProductions(Model model) {
		List<Production> productions = productionService.getProductions();
		model.addAttribute("productions", productions);
		return "production/production/list";
	}
	
	@GetMapping("/{id}")
	public String getProductionDetails(@PathVariable("id") Integer id, Model model) {
		Production production = productionService.findProduction(id);
		model.addAttribute("production", production);
		return "production/production/details";
	}
	@GetMapping("/new")
	public String showNewProductionForm(Model model) {
		Production production = new Production();
		model.addAttribute("production", production);
		return "production/production/new";
	}

	@PostMapping
	public String saveProduction(@ModelAttribute("production") Production production) {
		productionService.save(production);
		return "redirect:/production";
	}

	@GetMapping("/edit/{id}")
	public String showEditProductionForm(@PathVariable("id") Integer id, Model model) {
		Production production = productionService.findProduction(id);
		model.addAttribute("production", production);
		return "production/production/edit";
	}

	@PutMapping("/{id}")
	public String updateProduction(@PathVariable("id") Integer id, @ModelAttribute("production") Production production) {
		productionService.save(production);
		return "redirect:/production";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProduction(@PathVariable("id") Integer id) {
		productionService.delete(id);
		return "redirect:/production";
	}
}
