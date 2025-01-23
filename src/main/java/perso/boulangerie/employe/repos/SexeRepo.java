package perso.boulangerie.employe.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.employe.models.Sexe;

public interface SexeRepo  extends JpaRepository<Sexe,String>{
	
}
