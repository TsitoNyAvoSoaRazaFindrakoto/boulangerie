package perso.boulangerie.client.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.client.models.VenteDetails;

public interface VenteDetailsRepo extends JpaRepository<VenteDetails,Integer>{
	
}
