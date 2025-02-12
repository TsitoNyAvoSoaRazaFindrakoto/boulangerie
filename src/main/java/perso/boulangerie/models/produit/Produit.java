package perso.boulangerie.models.produit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProduit;

	private String nom;
	private String description;
	private BigDecimal prixUnitaire;

	@ManyToOne
	@JoinColumn(name = "idProduitCategorie")
	private ProduitCategorie categorie;
	@ManyToOne
	@JoinColumn(name = "idUnite")
	private Unite unite;

	@OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ProduitsRecettes> recettes = new ArrayList<>();

	@OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<PrixProduit> prix = new ArrayList<>();

	public void setRecettes(List<ProduitsRecettes> recetteslist) {
		recettes.clear();
		if (recetteslist != null) {
			for (ProduitsRecettes recette : recetteslist) {
				recette.setProduit(this);
			}
			this.recettes = recetteslist;

		}
	}

	public void setPrix(List<PrixProduit> prix) {
		this.prix.clear();
		if (prix != null) {
			for (PrixProduit prixProduit : prix) {
				prixProduit.setProduit(this);
			}
			this.prix = prix;
		}
	}

}
