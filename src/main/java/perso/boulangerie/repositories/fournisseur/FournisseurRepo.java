package perso.boulangerie.repositories.fournisseur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import perso.boulangerie.models.fournisseur.Fournisseur;

import java.util.List;

public interface FournisseurRepo extends JpaRepository<Fournisseur, Integer> {
	List<Fournisseur> findByEtat(boolean etat);

	@Modifying
	@Query(value = "update fournisseur set etat=false where id_fournisseur=:idFournisseur",nativeQuery = true)
	void rompreContrat(@Param("idFournisseur") Integer id);
}
