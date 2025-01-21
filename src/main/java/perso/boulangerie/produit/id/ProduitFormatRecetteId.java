package perso.boulangerie.produit.id;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitFormatRecetteId implements Serializable{
		private Integer ingredient;
		private Integer produitFormat;
}
