package perso.boulangerie.repositories.fournisseur;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.models.fournisseur.Fournisseur;
import perso.boulangerie.models.fournisseur.IngredientsFournisseurs;
import perso.boulangerie.models.fournisseur.id.IngredientsFournisseursId;
import perso.boulangerie.models.produit.Ingredient;

import java.util.List;



public interface IngredientsFournisseursRepo extends JpaRepository<IngredientsFournisseurs,IngredientsFournisseursId> {
	List<IngredientsFournisseurs> findByFournisseur(Fournisseur fournisseur);
	List<IngredientsFournisseurs> findByIngredient(Ingredient ingredient);
}
