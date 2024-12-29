package perso.boulangerie.client.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.client.models.Vente;

public interface VenteRepo extends JpaRepository<Vente,Integer>{
	
}
