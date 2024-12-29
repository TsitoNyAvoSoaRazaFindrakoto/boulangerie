package perso.boulangerie.fournisseur.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.fournisseur.id.IngredientsFournisseursId;
import perso.boulangerie.fournisseur.models.Fournisseur;
import perso.boulangerie.fournisseur.models.IngredientsFournisseurs;
import perso.boulangerie.fournisseur.repos.IngredientsFournisseursRepo;
import perso.boulangerie.produit.models.Ingredient;

@Service
public class IngredientsFournisseursService {
	@Autowired private IngredientsFournisseursRepo IngredientsFournisseursRepo;

	public IngredientsFournisseurs save(IngredientsFournisseurs IngredientsFournisseurs){
		return IngredientsFournisseursRepo.save(IngredientsFournisseurs);
	}

	public List<IngredientsFournisseurs> getIngredientsFournisseurss(){
		return IngredientsFournisseursRepo.findAll();
	}

	public IngredientsFournisseurs findIngredientsFournisseurs(IngredientsFournisseursId id){
		return IngredientsFournisseursRepo.findById(id).orElseThrow(() -> new RuntimeException("IngredientsFournisseurs not found with founisseur-id: " + id.getFournisseur() + " and ingredient-id:" + id.getIngredient()));
	}

	public List<IngredientsFournisseurs> findByFournisseur(Fournisseur f){
		return IngredientsFournisseursRepo.findByFournisseur(f);
	}

	public List<IngredientsFournisseurs> findByIngredient(Ingredient i){
		return IngredientsFournisseursRepo.findByIngredient(i);
	}

	public void deleteIngredientsFournisseurs(IngredientsFournisseurs IngredientsFournisseurs){
		IngredientsFournisseursRepo.delete(IngredientsFournisseurs);
	}
}
