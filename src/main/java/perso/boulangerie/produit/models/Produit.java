package perso.boulangerie.produit.models;

import java.math.BigDecimal;
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
    @JoinColumn(name = "idUnite")
    private Unite unite;

    @ManyToOne
    @JoinColumn(name = "idProduitCategorie")
    private ProduitCategorie categorieProduit;

		@Transient
		List<ProduitsRecettes> recette;

		public void setProduitRecette(){
			for (ProduitsRecettes produitsRecettes : recette) {
				produitsRecettes.setProduit(this);
			}
		}
}
