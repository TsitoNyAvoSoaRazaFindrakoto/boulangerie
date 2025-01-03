package perso.boulangerie.client.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.client.models.VenteFacture;
import perso.boulangerie.client.models.VenteFactureDetails;
import java.util.List;


public interface VenteFactureDetailsRepo extends JpaRepository<VenteFactureDetails,Integer>{
	List<VenteFactureDetails> findByVenteFacture(VenteFacture venteFacture);
}
