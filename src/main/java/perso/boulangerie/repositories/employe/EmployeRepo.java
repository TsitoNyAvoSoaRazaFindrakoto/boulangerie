package perso.boulangerie.repositories.employe;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import perso.boulangerie.models.employe.Employe;


public interface EmployeRepo extends JpaRepository<Employe, Integer> {

	@Query(value = """
			select * from v_vendeur
			""", nativeQuery = true)
	List<Employe> findAllVendeurs();

	@Query(value = """
			select vd.id_employe,vd.id_sexe,vd.id_type_employe,vd.nom,vd.prenoms,vd.date_embauche,vd.date_naissance,vd.est_employe,vtc.commission_vendeur as commission from v_vendeur vd join (select id_employe , sum(commission_vendeur) as commission_vendeur from vente group by id_employe) vtc on vd.id_employe = vtc.id_employe
			""", nativeQuery = true)
	List<Employe> findAllVendeursWithComission();

	@Query(value = """
			    SELECT vd.id_employe,vd.id_sexe, vd.id_type_employe, vd.nom, vd.prenoms, vd.date_embauche, vd.date_naissance, vd.est_employe, vtc.commission_vendeur AS commission
			    FROM v_vendeur vd
			    JOIN (
			        SELECT
			            id_employe,
			            SUM(commission_vendeur) AS commission_vendeur
			        FROM vente
			        WHERE (:minPeriod IS NULL OR date_vente >= CAST(:minPeriod AS date))
			          AND (:maxPeriod IS NULL OR date_vente <= CAST(:maxPeriod AS date))
			        GROUP BY id_employe
			        HAVING (:minCommission IS NULL OR SUM(commission_vendeur) >= CAST(:minCommission AS numeric))
			           AND (:maxCommission IS NULL OR SUM(commission_vendeur) <= CAST(:maxCommission AS numeric))
			    ) vtc
			    ON vd.id_employe = vtc.id_employe
			""", nativeQuery = true)
	List<Employe> findAllVendeursByCriteria(@Param("minCommission") BigDecimal minCommission,
			@Param("maxCommission") BigDecimal maxCommission, @Param("minPeriod") String minPeriod,
			@Param("maxPeriod") String maxPeriod);
}