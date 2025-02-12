package perso.boulangerie.repositories.client;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import perso.boulangerie.models.client.Client;


public interface ClientRepo extends JpaRepository<Client, Integer> {
	@Query(value = """
			SELECT DISTINCT c.*
			FROM Client c
			JOIN Vente v ON c.id_client = v.id_client
			WHERE
					(:startDateVente IS NULL OR :endDateVente IS NULL OR
					 (v.date_vente IS NOT NULL AND v.date_vente::DATE BETWEEN CAST(:startDateVente AS DATE) AND CAST(:endDateVente AS DATE)))
				AND (:startDateLivree IS NULL OR :endDateLivree IS NULL OR
						 (v.date_livree IS NOT NULL AND v.date_livree::DATE BETWEEN CAST(:startDateLivree AS DATE) AND CAST(:endDateLivree AS DATE)))
			""", nativeQuery = true)
	List<Client> findClientsByVenteAndLivraisonIntervals(@Param("startDateVente") String startDateVente,
			@Param("endDateVente") String endDateVente, @Param("startDateLivree") String startDateLivree,
			@Param("endDateLivree") String endDateLivree);
}
