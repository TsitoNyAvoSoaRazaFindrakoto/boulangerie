package perso.boulangerie.produit.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.produit.models.Produit;
import java.util.List;


public interface ProduitRepo extends JpaRepository<Produit,Integer>{
	List<Produit> findByNom(String nom);

	List<Produit> findByNomLike(String nom);
}
