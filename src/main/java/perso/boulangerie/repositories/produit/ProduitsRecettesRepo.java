package perso.boulangerie.repositories.produit;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.models.produit.Ingredient;
import perso.boulangerie.models.produit.Produit;
import perso.boulangerie.models.produit.ProduitsRecettes;
import perso.boulangerie.models.produit.id.ProduitsRecettesId;

import java.util.List;


public interface ProduitsRecettesRepo extends JpaRepository<ProduitsRecettes,ProduitsRecettesId> {

	List<ProduitsRecettes> findByProduit(Produit produit);

	List<ProduitsRecettes> findByIngredient(Ingredient ingredient);
	
}
