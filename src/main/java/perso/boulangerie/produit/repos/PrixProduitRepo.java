package perso.boulangerie.produit.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.produit.models.PrixProduit;
import perso.boulangerie.produit.models.Produit;

import java.util.List;


public interface PrixProduitRepo extends JpaRepository<PrixProduit,Integer>{
	List<PrixProduit> findByProduit(Produit produit);
}