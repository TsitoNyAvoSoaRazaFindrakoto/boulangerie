package perso.boulangerie.models.produit;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import perso.boulangerie.models.produit.id.ProduitsRecettesId;

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
	@JsonBackReference
	private Produit produit;

	private BigDecimal quantite;
	private String instruction;
}
