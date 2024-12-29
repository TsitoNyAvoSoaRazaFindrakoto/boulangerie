package perso.boulangerie.production.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.production.models.Production;
import perso.boulangerie.production.models.ProductionDetails;
import java.util.List;


public interface ProductionDetailsRepo extends JpaRepository<ProductionDetails,Integer>{
	List<ProductionDetails> findByProduction(Production production);
}
