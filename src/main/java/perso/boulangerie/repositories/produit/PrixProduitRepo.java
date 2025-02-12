package perso.boulangerie.repositories.produit;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.models.produit.PrixProduit;
import perso.boulangerie.models.produit.Produit;

import java.util.List;


public interface PrixProduitRepo extends JpaRepository<PrixProduit,Integer>{
	List<PrixProduit> findByProduit(Produit produit);
}