package perso.boulangerie.produit.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.produit.models.Format;

public interface FormatRepo extends JpaRepository<Format,Integer>{
	
}