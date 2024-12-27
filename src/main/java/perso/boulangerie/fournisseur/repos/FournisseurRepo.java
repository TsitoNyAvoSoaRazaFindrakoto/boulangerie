package perso.boulangerie.fournisseur.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import perso.boulangerie.fournisseur.models.Fournisseur;


public interface FournisseurRepo extends JpaRepository<Fournisseur, Integer> {
	
}
