package perso.boulangerie.produit.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.produit.id.ProduitsRecettesId;
import perso.boulangerie.produit.models.Ingredient;
import perso.boulangerie.produit.models.Produit;
import perso.boulangerie.produit.models.ProduitsRecettes;
import java.util.List;


public interface ProduitsRecettesRepo extends JpaRepository<ProduitsRecettes,ProduitsRecettesId> {

	List<ProduitsRecettes> findByProduit(Produit produit);

	List<ProduitsRecettes> findByIngredient(Ingredient ingredient);
	
}
