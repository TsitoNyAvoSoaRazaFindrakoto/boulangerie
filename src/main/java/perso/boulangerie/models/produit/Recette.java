package perso.boulangerie.models.produit;

import java.math.BigDecimal;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public class Recette {
	@Id
	@ManyToOne
	@JoinColumn(name = "idIngredient")
	private Ingredient ingredient;

	private BigDecimal quantite;
	private String instruction;
}
