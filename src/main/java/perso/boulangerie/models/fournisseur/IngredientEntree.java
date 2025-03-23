package perso.boulangerie.models.fournisseur;

import jakarta.persistence.*;
import lombok.Data;
import perso.boulangerie.models.produit.Ingredient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class IngredientEntree {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idIngredientEntree;

	private BigDecimal quantite;
	private LocalDateTime dateEntree;
	private BigDecimal prixUnitaire;

	@ManyToOne
	@JoinColumn(name = "idFournisseur", referencedColumnName = "idFournisseur")
	private Fournisseur fournisseur;

	@ManyToOne
	@JoinColumn(name = "idIngredient", referencedColumnName = "idIngredient")
	private Ingredient ingredient;
}
