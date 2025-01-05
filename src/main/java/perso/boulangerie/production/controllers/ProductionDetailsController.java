package perso.boulangerie.production.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import perso.boulangerie.production.models.ProductionDetails;
import perso.boulangerie.production.services.ProductionDetailsService;
import java.util.List;

@Controller
@RequestMapping("/productionDetails")
public class ProductionDetailsController {

	@Autowired
	private ProductionDetailsService productionDetailsService;

	@GetMapping
	public String getAllProductionDetails(Model model) {
		List<ProductionDetails> productionDetailsList = productionDetailsService.getProductions();
		model.addAttribute("productionDetailsList", productionDetailsList);
		return "productionDetails/list";
	}

	@GetMapping("/{id}")
	public String getProductionDetailsById(@PathVariable Integer id, Model model) {
		ProductionDetails productionDetails = productionDetailsService.findProduction(id);
		model.addAttribute("productionDetails", productionDetails);
		return "productionDetails/view";
	}

	@GetMapping("/create")
	public String createProductionDetailsForm(Model model) {
		model.addAttribute("productionDetails", new ProductionDetails());
		return "productionDetails/create";
	}

	@PostMapping
	public String saveProductionDetails(@ModelAttribute ProductionDetails productionDetails) {
		productionDetailsService.save(productionDetails);
		return "redirect:/productionDetails";
	}

	@GetMapping("/edit/{id}")
	public String editProductionDetailsForm(@PathVariable Integer id, Model model) {
		ProductionDetails productionDetails = productionDetailsService.findProduction(id);
		model.addAttribute("productionDetails", productionDetails);
		return "productionDetails/edit";
	}

	@PostMapping("/update/{id}")
	public String updateProductionDetails(@ModelAttribute ProductionDetails productionDetails) {
		productionDetailsService.save(productionDetails);
		return "redirect:/productionDetails";
	}

	@GetMapping("/delete/{id}")
	public String deleteProductionDetails(@PathVariable Integer id) {
		productionDetailsService.delete(id);
		return "redirect:/productionDetails";
	}
}
