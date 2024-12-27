package perso.boulangerie.produit.models;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;
import perso.boulangerie.produit.id.ProduitsRecettesId;

@Data
@Entity
@IdClass(ProduitsRecettesId.class)
public class ProduitsRecettes {
	@Id
	@ManyToOne
	@JoinColumn(name = "idIngredient")
	private Ingredient ingredient;

	@Id
	@ManyToOne
	@JoinColumn(name = "idProduit")
	private Produit produit;

	private BigDecimal quantite;
	private String instruction;
}
