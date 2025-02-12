package perso.boulangerie.repositories.client;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.models.client.Vente;


public interface VenteRepo extends JpaRepository<Vente,Integer>{
	
}
