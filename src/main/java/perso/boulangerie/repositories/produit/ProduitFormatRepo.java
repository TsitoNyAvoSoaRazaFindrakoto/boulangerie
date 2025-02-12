package perso.boulangerie.repositories.produit;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.models.produit.Produit;
import perso.boulangerie.models.produit.ProduitFormat;

import java.util.List;


public interface ProduitFormatRepo extends JpaRepository<ProduitFormat,Integer>{
	List<ProduitFormat> findByProduit(Produit produit);
}
