package perso.boulangerie.client.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import perso.boulangerie.client.models.Vente;
import perso.boulangerie.client.models.VenteFacture;
import java.util.List;


public interface VenteFactureRepo extends JpaRepository<VenteFacture,Integer>{
	List<VenteFacture> findByVente(Vente vente);

	// Requête pour chercher par format
    @Query(value = "SELECT Ve.* FROM Produit_Format AS Fo " +
                   "JOIN Vente_Facture AS Ve ON Fo.Id_Produit_Format = Ve.Id_Produit_Format " +
                   "WHERE Fo.Id_Format = :idFormat", nativeQuery = true)
    List<VenteFacture> findByFormat(@Param("idFormat") Integer idFormat);

    // Requête pour chercher par produit
    @Query(value = "SELECT Ve.* FROM Produit_Format AS Fo " +
                   "JOIN Vente_Facture AS Ve ON Fo.Id_Produit_Format = Ve.Id_Produit_Format " +
                   "WHERE Fo.Id_Produit = :idProduit", nativeQuery = true)
    List<VenteFacture> findByProduit(@Param("idProduit") Integer idProduit);

    // Requête pour chercher par combinaison produit et format
    @Query(value = "SELECT Ve.* FROM Produit_Format AS Fo " +
                   "JOIN Vente_Facture AS Ve ON Fo.Id_Produit_Format = Ve.Id_Produit_Format " +
                   "WHERE Fo.Id_Produit = :idProduit AND Fo.Id_Format = :idFormat", nativeQuery = true)
    List<VenteFacture> findByProduitFormat(@Param("idProduit") Integer idProduit, @Param("idFormat") Integer idFormat);

    // Requête pour chercher par catégorie
    @Query(value = "SELECT Ve.* FROM Produit_Format AS Fo " +
                   "JOIN Vente_Facture AS Ve ON Fo.Id_Produit_Format = Ve.Id_Produit_Format " +
                   "JOIN Produit AS Po ON Fo.Id_Produit = Po.Id_Produit " +
                   "WHERE Po.Id_Produit_Categorie = :idProduitCategorie", nativeQuery = true)
    List<VenteFacture> findByCategorie(@Param("idProduitCategorie") Integer idProduitCategorie);

    // Requête pour chercher par combinaison catégorie et format
    @Query(value = "SELECT Ve.* FROM Produit_Format AS Fo " +
                   "JOIN Vente_Facture AS Ve ON Fo.Id_Produit_Format = Ve.Id_Produit_Format " +
                   "JOIN Produit AS Po ON Fo.Id_Produit = Po.Id_Produit " +
                   "WHERE Po.Id_Produit_Categorie = :idProduitCategorie AND Fo.Id_Format = :idFormat", nativeQuery = true)
    List<VenteFacture> findByCategorieFormat(@Param("idProduitCategorie") Integer idProduitCategorie, @Param("idFormat") Integer idFormat);
}

