package perso.boulangerie.repositories.produit;

import java.time.YearMonth;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import perso.boulangerie.models.produit.ProduitConseil;

import java.time.LocalDate;

public interface ProduitConseilRepo extends JpaRepository<ProduitConseil, Integer> {
	// 1. Filter for current month
	@Query(value = "SELECT * FROM Produit_Conseil " + "WHERE date_debut >= DATE_TRUNC('month', CURRENT_DATE) "
			+ "AND date_debut < (DATE_TRUNC('month', CURRENT_DATE) + INTERVAL '1 month')", nativeQuery = true)
	List<ProduitConseil> findAllForCurrentMonth();

	// 2. Filter by specific year and month
	@Query(value = "SELECT * FROM Produit_Conseil " + "WHERE EXTRACT(YEAR FROM date_debut) = :#{#yearMonth.year} "
			+ "AND EXTRACT(MONTH FROM date_debut) = :#{#yearMonth.monthValue}", nativeQuery = true)
	List<ProduitConseil> findAllByYearMonth(@Param("yearMonth") YearMonth yearMonth);

	List<ProduitConseil> findByDateDebut(LocalDate dateDebut);

	// 3. Filter by year only
	@Query(value = "SELECT * FROM Produit_Conseil WHERE EXTRACT(YEAR FROM date_debut) = :year", nativeQuery = true)
	List<ProduitConseil> findAllByYear(@Param("year") int year);

}
