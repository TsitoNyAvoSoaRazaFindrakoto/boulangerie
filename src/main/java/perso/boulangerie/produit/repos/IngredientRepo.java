package perso.boulangerie.produit.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.produit.models.Ingredient;
import java.util.List;


public interface IngredientRepo extends JpaRepository<Ingredient,Integer>{
	List<Ingredient> findByNom(String nom);
}
