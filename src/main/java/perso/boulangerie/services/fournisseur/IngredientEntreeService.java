package perso.boulangerie.services.fournisseur;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.models.fournisseur.IngredientEntree;
import perso.boulangerie.models.produit.Ingredient;
import perso.boulangerie.repositories.fournisseur.IngredientEntreeRepo;
import perso.boulangerie.repositories.produit.IngredientRepo;


@Service
public class IngredientEntreeService {
	@Autowired private IngredientEntreeRepo IngredientEntreeRepo;
	@Autowired private IngredientRepo IngredientRepo;

	public IngredientEntree save(IngredientEntree IngredientEntree){
		return IngredientEntreeRepo.save(IngredientEntree);
	}

	public List<IngredientEntree> getIngredientEntrees(){
		return IngredientEntreeRepo.findAll();
	}

	public IngredientEntree findIngredientEntree(Integer id){
		return IngredientEntreeRepo.findById(id).orElseThrow(() -> new RuntimeException("IngredientsEntree not found with id: " + id));
	}

	public List<IngredientEntree> getStockIngredient(Integer idIngredient){
		return IngredientEntreeRepo.findStockByIngredient(idIngredient);
	}

	public HashMap<Integer,List<IngredientEntree>> getStockGroupByIngrdient(){
		HashMap<Integer,List<IngredientEntree>> stock = new HashMap<Integer,List<IngredientEntree>>();
		for (Ingredient ingredient : IngredientRepo.findAll()){
			stock.put(ingredient.getIdIngredient(), IngredientEntreeRepo.findStockByIngredient(ingredient.getIdIngredient()));
		}
		return stock;
	}

	public void deleteIngredientEntree(IngredientEntree IngredientEntree){
		IngredientEntreeRepo.delete(IngredientEntree);
	}
}