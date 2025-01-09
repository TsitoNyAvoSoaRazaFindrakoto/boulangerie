package perso.boulangerie.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import perso.boulangerie.client.models.VenteFacture;
import perso.boulangerie.client.services.VenteFactureService;
import perso.boulangerie.produit.models.ProduitCategorie;
import perso.boulangerie.produit.repos.FormatRepo;
import perso.boulangerie.produit.repos.ProduitCategorieRepository;
import perso.boulangerie.produit.repos.ProduitRepo;

import java.util.List;

@Controller
@RequestMapping("/client/vente-facture")
public class VenteFactureController {

	private VenteFactureService venteFactureService;
	private FormatRepo formatRepository;
	private ProduitRepo produitRepo;
	private ProduitCategorieRepository categorieRepository;
	

	public VenteFactureController(VenteFactureService venteFactureService, FormatRepo formatRepository,
			ProduitRepo produitRepo, ProduitCategorieRepository categorieRepository) {
		this.venteFactureService = venteFactureService;
		this.formatRepository = formatRepository;
		this.produitRepo = produitRepo;
		this.categorieRepository = categorieRepository;
	}

	@GetMapping
	public String getAllVentes(Model model) {
		List<VenteFacture> venteFactures = venteFactureService.getVenteFactures();
		model.addAttribute("venteFactures", venteFactures);
		model.addAttribute("format", formatRepository.findAll());
		model.addAttribute("produit", produitRepo.findAll());
		model.addAttribute("produitCategorie", categorieRepository.findAll());
		return "client/vente-facture/list";
	}

	@GetMapping("/critere1")
public String getVenteCritere1(Model model, @RequestParam(required = false) Integer idProduit, @RequestParam(required = false) Integer IdFormat) {
    List<VenteFacture> venteFactures = venteFactureService.getProduit(IdFormat, idProduit);
    model.addAttribute("venteFactures", venteFactures);
    model.addAttribute("format", formatRepository.findAll());
    model.addAttribute("produit", produitRepo.findAll());
    model.addAttribute("produitCategorie", categorieRepository.findAll());
    return "client/vente-facture/list";
}

@GetMapping("/critere2")
public String getVenteCritere2(Model model,@RequestParam(required = false) Integer idCategorie, @RequestParam(required = false) Integer IdFormat) {
    List<VenteFacture> venteFactures = venteFactureService.getCategorie(IdFormat, idCategorie);
    model.addAttribute("venteFactures", venteFactures);
    model.addAttribute("format", formatRepository.findAll());
    model.addAttribute("produit", produitRepo.findAll());
    model.addAttribute("produitCategorie", categorieRepository.findAll());
    return "client/vente-facture/list";
}

	@GetMapping("/{id}")
	public String getVenteById(@PathVariable Integer id, Model model) {
		VenteFacture venteFacture = venteFactureService.getVenteFacture(id);
		model.addAttribute("venteFacture", venteFacture);
		return "client/vente-facture/detail";
	}

	@GetMapping("/new")
	public String createVenteFactureForm(Model model) {
		model.addAttribute("venteFacture", new VenteFacture());
		return "client/vente-facture/form";
	}

	@PostMapping
	public String saveVenteFacture(@ModelAttribute VenteFacture venteFacture) {
		venteFactureService.save(venteFacture);
		return "redirect:/client/vente-facture";
	}

	@GetMapping("/edit/{id}")
	public String editVenteFactureForm(@PathVariable Integer id, Model model) {
		VenteFacture venteFacture = venteFactureService.getVenteFacture(id);
		model.addAttribute("venteFacture", venteFacture);
		return "client/vente-facture/form";
	}

	@PutMapping("/{id}")
	public String updateVenteFacture(@PathVariable Integer id, @ModelAttribute VenteFacture venteFacture) {
		venteFacture.setIdVenteFacture(id);
		venteFactureService.save(venteFacture);
		return "redirect:/client/vente-facture";
	}

	@DeleteMapping("/delete/{id}")
	public String deleteVenteFacture(@PathVariable Integer id) {
		venteFactureService.deleteFactureVente(id);
		return "redirect:/client/vente-facture";
	}

}
