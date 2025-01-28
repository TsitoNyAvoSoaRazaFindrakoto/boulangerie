package perso.boulangerie.produit.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import perso.boulangerie.produit.models.PrixProduit;
import perso.boulangerie.produit.models.Produit;
import perso.boulangerie.produit.repos.PrixProduitRepo;
import perso.boulangerie.produit.repos.ProduitRepo;

@Controller
@RequestMapping("/produit/prix")
@AllArgsConstructor
public class PrixProduitController {

    private final ProduitRepo produitRepo;
    private final PrixProduitRepo prixProduitRepo;

    @GetMapping
    public String getAllPrixProduits(Model model) {
        List<PrixProduit> prixProduits = prixProduitRepo.findAll();
        model.addAttribute("prixProduits", prixProduits);
        return "produit/prix/list";
    }

    @GetMapping("/{id}")
    public String getPrixProduitById(@PathVariable Integer id, Model model) {
        PrixProduit prixProduit = prixProduitRepo.findById(id).orElse(null);
        model.addAttribute("prixProduit", prixProduit);
        return "produit/prix/detail";
    }

    @GetMapping("/new/{id}")
    public String createPrixProduitForm(@PathVariable Integer id, Model model) {
        model.addAttribute("prixProduit", new PrixProduit());
        model.addAttribute("produits", produitRepo.findAll());
        return "produit/prix/form";
    }

    @PostMapping
    public String savePrixProduit(@ModelAttribute PrixProduit prixProduit) {
        prixProduitRepo.save(prixProduit);
        return "redirect:/produit/prix";
    }

    @GetMapping("/edit/{id}")
    public String editPrixProduitForm(@PathVariable Integer id, Model model) {
        PrixProduit prixProduit = prixProduitRepo.findById(id).orElse(null);
        model.addAttribute("prixProduit", prixProduit);
        model.addAttribute("produits", produitRepo.findAll());
        return "produit/prix/form";
    }

    @PostMapping("/update/{id}")
    public String updatePrixProduit(@ModelAttribute PrixProduit prixProduit) {
        prixProduitRepo.save(prixProduit);
        return "redirect:/produit/prix";
    }

    @DeleteMapping("/delete/{id}")
    public String deletePrixProduit(@PathVariable Integer id) {
        prixProduitRepo.deleteById(id);
        return "redirect:/produit/prix";
    }
}