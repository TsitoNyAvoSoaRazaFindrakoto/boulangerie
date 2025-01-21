package perso.boulangerie.produit.models;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import perso.boulangerie.produit.id.ProduitFormatRecetteId;

@Entity
@Data
@IdClass(ProduitFormatRecetteId.class)
public class ProduitFormatRecette {
	@Id
	@ManyToOne
	@JoinColumn(name = "idIngredient")
	private Ingredient ingredient;

	@Id
	@ManyToOne
	@JoinColumn(name = "idProduitFormat")
	@JsonBackReference
	private ProduitFormat produit;

	private BigDecimal quantite;
	private String instruction;
}
