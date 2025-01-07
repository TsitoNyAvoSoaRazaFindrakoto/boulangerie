package perso.boulangerie.produit.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@Entity
public class Format {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdProduitFormat;

	private String nom;
	private BigDecimal multPrix,multRecette;
}
