package perso.boulangerie.client.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import perso.boulangerie.client.models.Client;

public interface ClientRepo extends JpaRepository<Client, Integer> {
    @Query(value = """
            SELECT DISTINCT c.*
            FROM Client c
            JOIN Vente v ON c.id_client = v.id_client
            WHERE (CAST(:dateVente AS DATE) IS NULL OR (v.date_vente IS NOT NULL AND v.date_vente::DATE = CAST(:dateVente AS DATE)))
              AND (CAST(:dateLivree AS DATE) IS NULL OR (v.date_livree IS NOT NULL AND v.date_livree::DATE = CAST(:dateLivree AS DATE)))
            """, nativeQuery = true)
    List<Client> findClientsByVenteAndLivraisonDate(
            @Param("dateVente") LocalDate dateVente,
            @Param("dateLivree") LocalDate dateLivree);
}


