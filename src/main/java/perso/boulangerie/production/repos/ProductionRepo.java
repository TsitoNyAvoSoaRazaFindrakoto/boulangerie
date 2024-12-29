package perso.boulangerie.production.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.production.models.Production;
import java.util.List;
import java.time.LocalDateTime;


public interface ProductionRepo extends JpaRepository<Production,Integer>{
	List<Production> findByDateProduction(LocalDateTime dateProduction);
}