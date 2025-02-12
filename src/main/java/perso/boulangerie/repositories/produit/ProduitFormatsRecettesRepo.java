package perso.boulangerie.repositories.produit;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.models.produit.Ingredient;
import perso.boulangerie.models.produit.ProduitFormat;
import perso.boulangerie.models.produit.ProduitFormatRecette;
import perso.boulangerie.models.produit.id.ProduitFormatRecetteId;
import java.util.List;

public interface ProduitFormatsRecettesRepo extends JpaRepository<ProduitFormatRecette, ProduitFormatRecetteId> {
	List<ProduitFormatRecette> findByProduitFormat(ProduitFormat produitFormat);

	List<ProduitFormatRecette> findByIngredient(Ingredient ingredient);
}
