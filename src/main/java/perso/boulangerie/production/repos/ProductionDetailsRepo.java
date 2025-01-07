package perso.boulangerie.production.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import perso.boulangerie.production.models.Production;
import perso.boulangerie.production.models.ProductionDetails;
import java.util.List;

public interface ProductionDetailsRepo extends JpaRepository<ProductionDetails, Integer> {
	List<ProductionDetails> findByProduction(Production production);

	@Query(value = "select pd.* from Production_Details as pd join Ingredient_Entree as Ing on pd.Id_Ingredient_Entree = Ing.Id_Ingredient_Entree where Ing.id_Ingredient = :idIngredient", nativeQuery = true)
	List<ProductionDetails> findByIngredient(@Param("idIngredient") Integer idIngredient);
}
