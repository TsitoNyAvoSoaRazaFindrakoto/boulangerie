package perso.boulangerie.fournisseur.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import perso.boulangerie.fournisseur.models.IngredientEntree;

public interface IngredientEntreeRepo extends JpaRepository<IngredientEntree,Integer>{
	
}
