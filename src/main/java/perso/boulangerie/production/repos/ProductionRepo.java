package perso.boulangerie.production.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import perso.boulangerie.production.models.Production;
import java.util.List;
import java.time.LocalDateTime;


public interface ProductionRepo extends JpaRepository<Production,Integer>{
	List<Production> findByDateProduction(LocalDateTime dateProduction);

	@Query(value = "select * from Stock_Produit ",nativeQuery = true)
	List<Production> findStock();

	@Query(value = "select * from Stock_Produit where id_produit = :idProduit order by date_production",nativeQuery = true)
	List<Production> findStockByIngredient(@Param("idProduit") Integer idProduit);
}