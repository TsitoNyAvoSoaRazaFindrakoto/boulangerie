package perso.boulangerie.repositories.produit;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.models.produit.Ingredient;

import java.util.List;


public interface IngredientRepo extends JpaRepository<Ingredient,Integer>{
	List<Ingredient> findByNom(String nom);
}
