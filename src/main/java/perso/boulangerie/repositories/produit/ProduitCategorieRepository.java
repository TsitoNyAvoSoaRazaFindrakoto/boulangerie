package perso.boulangerie.repositories.produit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import perso.boulangerie.models.produit.ProduitCategorie;

@Repository
public interface ProduitCategorieRepository extends JpaRepository<ProduitCategorie, Integer> {
    // Ici vous pouvez ajouter des méthodes personnalisées pour interagir avec la base de données, si nécessaire
}