package perso.boulangerie.produit.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.produit.models.Produit;
import perso.boulangerie.produit.models.ProduitFormat;
import java.util.List;


public interface ProduitFormatRepo extends JpaRepository<ProduitFormat,Integer>{
	List<ProduitFormat> findByProduit(Produit produit);
}
