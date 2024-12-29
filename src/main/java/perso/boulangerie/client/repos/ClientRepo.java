package perso.boulangerie.client.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.client.models.Client;

public interface ClientRepo extends JpaRepository<Client,Integer>{
	
}
