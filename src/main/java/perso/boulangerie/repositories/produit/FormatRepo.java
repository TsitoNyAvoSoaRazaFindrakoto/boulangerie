package perso.boulangerie.repositories.produit;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.models.produit.Format;


public interface FormatRepo extends JpaRepository<Format,Integer>{
	
}