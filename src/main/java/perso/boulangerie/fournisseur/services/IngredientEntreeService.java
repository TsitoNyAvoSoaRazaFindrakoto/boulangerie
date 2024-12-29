package perso.boulangerie.fournisseur.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.fournisseur.models.IngredientEntree;
import perso.boulangerie.fournisseur.repos.IngredientEntreeRepo;

@Service
public class IngredientEntreeService {
	@Autowired private IngredientEntreeRepo IngredientEntreeRepo;

	public IngredientEntree save(IngredientEntree IngredientEntree){
		return IngredientEntreeRepo.save(IngredientEntree);
	}

	public List<IngredientEntree> getIngredientEntrees(){
		return IngredientEntreeRepo.findAll();
	}

	public IngredientEntree findIngredientEntree(Integer id){
		return IngredientEntreeRepo.findById(id).orElseThrow(() -> new RuntimeException("IngredientsEntree not found with id: " + id));
	}

	public void deleteIngredientEntree(IngredientEntree IngredientEntree){
		IngredientEntreeRepo.delete(IngredientEntree);
	}
}
