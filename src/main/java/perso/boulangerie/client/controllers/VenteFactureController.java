package perso.boulangerie.client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import perso.boulangerie.client.models.Vente;
import perso.boulangerie.client.models.VenteFacture;
import perso.boulangerie.client.repos.VenteRepo;
import perso.boulangerie.client.services.VenteFactureService;
import perso.boulangerie.produit.repos.FormatRepo;
import perso.boulangerie.produit.repos.ProduitCategorieRepository;
import perso.boulangerie.produit.repos.ProduitFormatRepo;
import perso.boulangerie.produit.repos.ProduitRepo;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/client/vente-facture")
public class VenteFactureController {

	private VenteFactureService venteFactureService;
	private VenteRepo venteRepo;
	private FormatRepo formatRepository;
	private ProduitRepo produitRepo;
	private ProduitCategorieRepository categorieRepository;
	private ProduitFormatRepo produitFormatRepo;

	public String showList(Model model, List<VenteFacture> venteFactures) {
		model.addAttribute("venteFactures", venteFactures);
		model.addAttribute("format", formatRepository.findAll());
		model.addAttribute("produit", produitRepo.findAll());
		model.addAttribute("produitCategorie", categorieRepository.findAll());
		return "client/vente-facture/list";
	}

	@GetMapping
	public String getAllVentesFactures(Model model) {
		List<VenteFacture> venteFactures = venteFactureService.getVenteFactures();
		return showList(model, venteFactures);
	}

	@GetMapping("/critere1")
	public String getVenteCritere1(Model model, @RequestParam(required = false) Integer idProduit,
			@RequestParam(required = false) Integer IdFormat) {
		List<VenteFacture> venteFactures = venteFactureService.getProduit(IdFormat, idProduit);
		return showList(model, venteFactures);
	}

	@GetMapping("/critere2")
	public String getVenteCritere2(Model model, @RequestParam(required = false) Integer idCategorie,
			@RequestParam(required = false) Integer IdFormat) {
		List<VenteFacture> venteFactures = venteFactureService.getCategorie(IdFormat, idCategorie);
		return showList(model, venteFactures);
	}

	@GetMapping("/{id}")
	public String getVenteById(@PathVariable Integer id, Model model) {
		VenteFacture venteFacture = venteFactureService.getVenteFacture(id);
		model.addAttribute("venteFacture", venteFacture);
		return "client/vente-facture/detail";
	}

	@GetMapping("/new/{id}")
	public String createVenteFactureForm(Model model, @PathVariable(required = false) Integer id,
			@SessionAttribute(name = "vente", required = false) Vente vente) {
		VenteFacture v = new VenteFacture();
		v.setVente(id == null ? vente : venteRepo.findById(id).get());
		model.addAttribute("venteFacture", v);
		model.addAttribute("produitFormats", produitFormatRepo.findAll());
		return "client/vente-facture/form";
	}

	@PostMapping
	public String saveVenteFacture(@ModelAttribute VenteFacture venteFacture) {
		venteFactureService.save(venteFacture);
		return "redirect:/client/vente-facture/new/"+venteFacture.getVente().getIdVente();
	}

	@GetMapping("/edit/{id}")
	public String editVenteFactureForm(@PathVariable Integer id, Model model) {
		VenteFacture venteFacture = venteFactureService.getVenteFacture(id);
		model.addAttribute("venteFacture", venteFacture);
		model.addAttribute("produitFormats", produitFormatRepo.findAll());
		return "client/vente-facture/form";
	}

	@PostMapping("/{id}")
	public String updateVenteFacture(@PathVariable Integer id, @ModelAttribute VenteFacture venteFacture) {
		venteFacture.setIdVenteFacture(id);
		venteFactureService.save(venteFacture);
		return "redirect:/client/vente-facture";
	}

	@GetMapping("/delete/{id}")
	public String deleteVenteFacture(@PathVariable Integer id) {
		return "redirect:/client/vente/"+venteFactureService.deleteFactureVente(id);
	}

}