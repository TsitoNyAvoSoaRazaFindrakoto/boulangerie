package perso.boulangerie.fournisseur.models;

import jakarta.persistence.*;
import lombok.Data;

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
	@JoinColumns({
			@JoinColumn(name = "idFournisseur", referencedColumnName = "idFournisseur"),
			@JoinColumn(name = "idIngredient", referencedColumnName = "idIngredient")
	})
	private IngredientsFournisseurs ingredientsFournisseurs;
}
