package perso.boulangerie.employe.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.employe.models.TypeEmploye;

public interface TypeEmployeRepo extends JpaRepository<TypeEmploye,String>{
	
}
