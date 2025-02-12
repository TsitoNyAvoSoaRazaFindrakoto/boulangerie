package perso.boulangerie.services.fournisseur;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.models.fournisseur.Fournisseur;
import perso.boulangerie.models.fournisseur.IngredientsFournisseurs;
import perso.boulangerie.models.fournisseur.id.IngredientsFournisseursId;
import perso.boulangerie.models.produit.Ingredient;
import perso.boulangerie.repositories.fournisseur.IngredientsFournisseursRepo;


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

	public IngredientsFournisseurs findIngredientsFournisseurs(Integer idFournisseur, Integer idIngredient){
		return IngredientsFournisseursRepo.findById(new IngredientsFournisseursId(idFournisseur, idIngredient)).orElseThrow(() -> new RuntimeException("IngredientsFournisseurs not found with founisseur-id: " + idFournisseur + " and ingredient-id:" + idIngredient));
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
