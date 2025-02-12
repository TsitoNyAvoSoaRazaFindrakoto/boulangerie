package perso.boulangerie.services.produit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.models.produit.Ingredient;
import perso.boulangerie.models.produit.Produit;
import perso.boulangerie.models.produit.ProduitsRecettes;
import perso.boulangerie.models.produit.id.ProduitsRecettesId;
import perso.boulangerie.repositories.produit.ProduitsRecettesRepo;


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
