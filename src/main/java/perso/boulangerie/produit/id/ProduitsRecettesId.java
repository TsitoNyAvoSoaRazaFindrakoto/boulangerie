package perso.boulangerie.produit.id;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProduitsRecettesId implements Serializable{
	private Integer ingredient;
	private Integer produit;

}
