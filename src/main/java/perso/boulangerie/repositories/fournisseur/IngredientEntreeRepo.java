package perso.boulangerie.repositories.fournisseur;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import perso.boulangerie.models.fournisseur.IngredientEntree;


public interface IngredientEntreeRepo extends JpaRepository<IngredientEntree,Integer>{
	@Query(value = "select * from Stock_Ingredient",nativeQuery = true)
	List<IngredientEntree> findStock();

	@Query(value = "select * from Stock_Ingredient where id_ingredient = :idIngredient order by date_entree",nativeQuery = true)
	List<IngredientEntree> findStockByIngredient(@Param("idIngredient") Integer idIngredient);
}
