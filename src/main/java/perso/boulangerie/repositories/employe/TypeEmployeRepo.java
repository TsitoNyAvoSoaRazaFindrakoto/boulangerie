package perso.boulangerie.repositories.employe;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.models.employe.TypeEmploye;


public interface TypeEmployeRepo extends JpaRepository<TypeEmploye,String>{
	
}
