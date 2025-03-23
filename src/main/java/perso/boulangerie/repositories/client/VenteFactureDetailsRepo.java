package perso.boulangerie.repositories.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import perso.boulangerie.models.client.VenteFacture;
import perso.boulangerie.models.client.VenteFactureDetails;

import java.util.List;


public interface VenteFactureDetailsRepo extends JpaRepository<VenteFactureDetails,Integer>{
	List<VenteFactureDetails> findByVenteFacture(VenteFacture venteFacture);

	@Modifying
	@Query(value = "DELETE FROM Vente_Facture_Details WHERE Id_Vente_Facture = :idVenteFacture", nativeQuery = true)
	public void deleteByIdVenteFacture(@Param("idVenteFacture")  Integer idVenteFacture);

	@Modifying
	@Query(value = "DELETE FROM Vente_Facture_Details WHERE Id_Vente_Facture in (select Id_Vente_Facture from Vente_Facture where Id_Vente = :idVente)", nativeQuery = true)
	public void deleteByIdVente(@Param("idVente")  Integer idVente);
}
