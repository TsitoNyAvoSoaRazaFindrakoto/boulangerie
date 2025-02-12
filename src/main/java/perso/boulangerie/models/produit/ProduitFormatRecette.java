package perso.boulangerie.models.produit;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import perso.boulangerie.models.produit.id.ProduitFormatRecetteId;

@EqualsAndHashCode(callSuper=true)
@Entity
@Data
@IdClass(ProduitFormatRecetteId.class)
public class ProduitFormatRecette extends Recette{

	@Id
	@ManyToOne
	@JoinColumn(name = "idProduitFormat")
	@JsonBackReference
	private ProduitFormat produitFormat;

}