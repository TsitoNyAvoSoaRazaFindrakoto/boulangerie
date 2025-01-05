package perso.boulangerie.produit.id;

import java.io.Serializable;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitsRecettesId implements Serializable{
	private Integer ingredient;
	private Integer produit;

}
