package perso.boulangerie.employe.repos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import perso.boulangerie.employe.models.Employe;

public interface EmployeRepo extends JpaRepository<Employe, Integer> {
	@Query(value = """
			select vd.*,vtc.commission_vendeur as commission from v_vendeur vd join (select id_employe , sum(commission_vendeur) as commission_vendeur from vente group by id_employe) vtc on vd.id_employe = vtc.id_employe
			""", nativeQuery = true)
	List<Employe> findAllVendeurs();

	@Query(value = """
			SELECT vd.*, vtc.commission_vendeur AS commission
			FROM v_vendeur vd
			JOIN (
			    SELECT
			        id_employe,
			        SUM(commission_vendeur) AS commission_vendeur
			    FROM vente
			    WHERE (:minPeriod IS NULL OR date_vente >= :minPeriod)
			      AND (:maxPeriod IS NULL OR date_vente <= :maxPeriod)
			    GROUP BY id_employe
			    HAVING (:minCommission IS NULL OR SUM(commission_vendeur) >= :minCommission)
			       AND (:maxCommission IS NULL OR SUM(commission_vendeur) <= :maxCommission)
			) vtc
			ON vd.id_employe = vtc.id_employe
			""", nativeQuery = true)
	List<Employe> findAllVendeursByCriteria(@Param("minCommission") BigDecimal minCommission,
			@Param("maxCommission") BigDecimal maxCommission, @Param("minPeriod") LocalDate minPeriod,
			@Param("maxPeriod") LocalDate maxPeriod);
}