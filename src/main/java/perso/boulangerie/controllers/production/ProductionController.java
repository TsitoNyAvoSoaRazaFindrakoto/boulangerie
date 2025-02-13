package perso.boulangerie.controllers.production;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import perso.boulangerie.models.production.Production;
import perso.boulangerie.services.production.ProductionService;
import perso.boulangerie.services.produit.ProduitFormatService;

import java.util.List;


@Controller
@RequestMapping("/production")
@AllArgsConstructor
public class ProductionController {

	private ProductionService productionService;
	private ProduitFormatService produitFormatService;

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
		model.addAttribute("produits", produitFormatService.getProduitFormats());
		model.addAttribute("production", production);
		return "production/production/form";
	}

	@PostMapping
	public String saveProduction(@ModelAttribute("production") Production production) {
		productionService.save(production);
		return "redirect:/production";
	}

	@GetMapping("/edit/{id}")
	public String showEditProductionForm(@PathVariable("id") Integer id, Model model) {
		Production production = productionService.findProduction(id);
		model.addAttribute("produits", produitFormatService.getProduitFormats());
		model.addAttribute("production", production);
		return "production/production/form";
	}

	@PostMapping("/{id}")
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
