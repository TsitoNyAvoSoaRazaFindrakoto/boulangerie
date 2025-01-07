package perso.boulangerie.produit.models;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ProduitFormat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdProduitFormat;

	private BigDecimal prixUnitaire;

	@ManyToOne
	@JoinColumn(name = "idProduit")
	private Produit produit;

	@ManyToOne
	@JoinColumn(name = "idFormat")
	private Format format;
}
