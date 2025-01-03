package perso.boulangerie.client.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.client.models.Vente;
import perso.boulangerie.client.models.VenteFacture;
import java.util.List;


public interface VenteFactureRepo extends JpaRepository<VenteFacture,Integer>{
	List<VenteFacture> findByVente(Vente vente);
}
