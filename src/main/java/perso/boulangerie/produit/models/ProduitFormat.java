package perso.boulangerie.produit.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class ProduitFormat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer IdProduitFormat;

	private BigDecimal prixUnitaire;

	private String nom;

	@ManyToOne
	@JoinColumn(name = "idProduit")
	private Produit produit;

	@ManyToOne
	@JoinColumn(name = "idFormat")
	private Format format;

	@OneToMany(mappedBy = "produitFormat", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProduitFormatRecette> recettes;
}
