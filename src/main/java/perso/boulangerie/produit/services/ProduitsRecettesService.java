package perso.boulangerie.produit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.produit.id.ProduitsRecettesId;
import perso.boulangerie.produit.models.Ingredient;
import perso.boulangerie.produit.models.Produit;
import perso.boulangerie.produit.models.ProduitsRecettes;
import perso.boulangerie.produit.repos.ProduitsRecettesRepo;

@Service
public class ProduitsRecettesService {
	@Autowired
	private ProduitsRecettesRepo ProduitsRecettesRepo;

	public List<ProduitsRecettes> getAllProduits() {
		return ProduitsRecettesRepo.findAll();
	}

	public ProduitsRecettes getProduitById(ProduitsRecettesId id) {
		return ProduitsRecettesRepo.findById(id).orElseThrow(()-> new RuntimeException("ProduitsRecettes not found with ingredient-id: "+id.getIngredient()+ " and produit-id: "+id.getProduit()));
	}

	public ProduitsRecettes save(ProduitsRecettes ProduitsRecettes) {
		return ProduitsRecettesRepo.save(ProduitsRecettes);
	}

	public List<ProduitsRecettes> findByProduit(Produit produit) {
		return ProduitsRecettesRepo.findByProduit(produit);
	}

	public List<ProduitsRecettes> findByIngredient(Ingredient ingredient) {
		return ProduitsRecettesRepo.findByIngredient(ingredient);
	}

	public void deleteProduit(ProduitsRecettesId id) {
		ProduitsRecettesRepo.deleteById(id);
	}
}
