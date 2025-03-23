package perso.boulangerie.repositories.produit;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.models.produit.Produit;

import java.util.List;


public interface ProduitRepo extends JpaRepository<Produit,Integer>{
	List<Produit> findByNom(String nom);

	List<Produit> findByNomLike(String nom);
}
