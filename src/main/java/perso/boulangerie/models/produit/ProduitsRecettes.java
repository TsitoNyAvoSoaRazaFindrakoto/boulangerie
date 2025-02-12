package perso.boulangerie.models.produit;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import perso.boulangerie.models.produit.id.ProduitsRecettesId;

@EqualsAndHashCode(callSuper=true)
@Data
@Entity
@IdClass(ProduitsRecettesId.class)
public class ProduitsRecettes extends Recette{

	@Id
	@ManyToOne
	@JoinColumn(name = "idProduit")
	@JsonBackReference
	private Produit produit;

}
