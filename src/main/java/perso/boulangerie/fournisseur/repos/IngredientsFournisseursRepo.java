package perso.boulangerie.fournisseur.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.fournisseur.id.IngredientsFournisseursId;
import perso.boulangerie.fournisseur.models.Fournisseur;
import perso.boulangerie.fournisseur.models.IngredientsFournisseurs;
import java.util.List;
import perso.boulangerie.produit.models.Ingredient;



public interface IngredientsFournisseursRepo extends JpaRepository<IngredientsFournisseurs,IngredientsFournisseursId> {
	List<IngredientsFournisseurs> findByFournisseur(Fournisseur fournisseur);
	List<IngredientsFournisseurs> findByIngredient(Ingredient ingredient);
}
