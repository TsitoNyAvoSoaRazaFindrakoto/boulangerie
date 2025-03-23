package perso.boulangerie.repositories.employe;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.models.employe.Sexe;


public interface SexeRepo  extends JpaRepository<Sexe,String>{
	
}
