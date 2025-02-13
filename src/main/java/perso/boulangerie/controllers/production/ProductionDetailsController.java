package perso.boulangerie.controllers.production;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import perso.boulangerie.models.production.ProductionDetails;
import perso.boulangerie.repositories.production.ProductionDetailsRepo;
import perso.boulangerie.repositories.produit.IngredientRepo;
import perso.boulangerie.services.production.ProductionDetailsService;

import java.util.List;

@Controller
@RequestMapping("/production/details")
@AllArgsConstructor
public class ProductionDetailsController {

    private ProductionDetailsService productionDetailsService;

    private IngredientRepo IngredientRepo;

    private ProductionDetailsRepo productionDetailsRepo;

    @GetMapping("/find-by-ingredient")
    public String getProductionDetailByIngredient(@RequestParam Integer id, Model model) {
        List<ProductionDetails> productionDetailsList = productionDetailsRepo.findByIngredient(id);
        model.addAttribute("productionDetailsList", productionDetailsList);
        model.addAttribute("ingredientList", IngredientRepo.findAll());
        return "/production/details/list";
    }

    @GetMapping
    public String getAllProductionDetails(Model model) {
        List<ProductionDetails> productionDetailsList = productionDetailsService.getProductions();
        model.addAttribute("productionDetailsList", productionDetailsList);
        model.addAttribute("ingredientList", IngredientRepo.findAll());
        return "/production/details/list";
    }

    @GetMapping("/{id}")
    public String getProductionDetailsById(@PathVariable Integer id, Model model) {
        ProductionDetails productionDetails = productionDetailsService.findProduction(id);
        model.addAttribute("productionDetails", productionDetails);
        return "/production/details/detail";
    }

    @GetMapping("/create")
    public String createProductionDetailsForm(Model model) {
        model.addAttribute("productionDetails", new ProductionDetails());
        return "/production/details/create";
    }

    @PostMapping
    public String saveProductionDetails(@ModelAttribute ProductionDetails productionDetails) {
        productionDetailsService.save(productionDetails);
        return "redirect:/production/details";
    }

    @GetMapping("/edit/{id}")
    public String editProductionDetailsForm(@PathVariable Integer id, Model model) {
        ProductionDetails productionDetails = productionDetailsService.findProduction(id);
        model.addAttribute("productionDetails", productionDetails);
        return "/production/details/edit";
    }

    @PostMapping("/update/{id}")
    public String updateProductionDetails(@ModelAttribute ProductionDetails productionDetails) {
        productionDetailsService.save(productionDetails);
        return "redirect:/production/details";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductionDetails(@PathVariable Integer id) {
        productionDetailsService.delete(id);
        return "redirect:/production/details";
    }
}
