package perso.boulangerie.production.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import perso.boulangerie.production.models.ProductionDetails;
import perso.boulangerie.production.repos.ProductionDetailsRepo;
import perso.boulangerie.production.services.ProductionDetailsService;
import perso.boulangerie.produit.repos.IngredientRepo;

import java.util.List;

@Controller
@RequestMapping("/production/production-details")
public class ProductionDetailsController {

	@Autowired
	private ProductionDetailsService productionDetailsService;

	@Autowired
	private IngredientRepo IngredientRepo;

	@Autowired
	private ProductionDetailsRepo productionDetailsRepo;

	@GetMapping("/find-by-ingredient")
	public String getProductionDetailByIngredient(@RequestParam Integer id,Model model) {
		List<ProductionDetails> productionDetailsList = productionDetailsRepo.findByIngredient(id);
		model.addAttribute("productionDetailsList", productionDetailsList);
		model.addAttribute("ingredientList", IngredientRepo.findAll());
		return "/production/production-details/list";
	}


	@GetMapping
	public String getAllProductionDetails(Model model) {
		List<ProductionDetails> productionDetailsList = productionDetailsService.getProductions();
		model.addAttribute("productionDetailsList", productionDetailsList);
		model.addAttribute("ingredientList", IngredientRepo.findAll());
		return "/production/production-details/list";
	}

	@GetMapping("/{id}")
	public String getProductionDetailsById(@PathVariable Integer id, Model model) {
		ProductionDetails productionDetails = productionDetailsService.findProduction(id);
		model.addAttribute("productionDetails", productionDetails);
		return "/production/production-details/view";
	}

	@GetMapping("/create")
	public String createProductionDetailsForm(Model model) {
		model.addAttribute("productionDetails", new ProductionDetails());
		return "/production/production-details/create";
	}

	@PostMapping
	public String saveProductionDetails(@ModelAttribute ProductionDetails productionDetails) {
		productionDetailsService.save(productionDetails);
		return "redirect:/production/production-details";
	}

	@GetMapping("/edit/{id}")
	public String editProductionDetailsForm(@PathVariable Integer id, Model model) {
		ProductionDetails productionDetails = productionDetailsService.findProduction(id);
		model.addAttribute("productionDetails", productionDetails);
		return "/production/production-details/edit";
	}

	@PutMapping("/update/{id}")
	public String updateProductionDetails(@ModelAttribute ProductionDetails productionDetails) {
		productionDetailsService.save(productionDetails);
		return "redirect:/production/production-details";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProductionDetails(@PathVariable Integer id) {
		productionDetailsService.delete(id);
		return "redirect:/production/production-details";
	}
}
