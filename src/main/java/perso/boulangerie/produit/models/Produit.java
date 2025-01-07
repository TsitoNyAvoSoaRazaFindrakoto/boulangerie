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

		@OneToMany(mappedBy = "produit", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
		private List<ProduitsRecettes> recettes;

		public void setRecettes(List<ProduitsRecettes> recetteslist) {
			if (recetteslist != null && !recetteslist.isEmpty() && recetteslist.get(0).getProduit() != null || recetteslist==null) {
				return;
			}
			for (ProduitsRecettes recette : recetteslist) {
				recette.setProduit(this);
			}
			this.recettes = recetteslist;
		}
		
}
