package perso.boulangerie.client.models;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import perso.boulangerie.produit.models.Produit;

@Data
@Entity
public class VenteFacture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenteFacture;

    private Integer quantite;
    private BigDecimal prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "idProduit")
    private Produit produit;

    @ManyToOne
    @JoinColumn(name = "idVente")
    private Vente vente;

		@Transient
		private List<VenteFactureDetails> factureDetails;

		public void setFactureDetails(List<VenteFactureDetails> factureDetails){
			if (factureDetails != null && !factureDetails.isEmpty() && factureDetails.get(0).getVenteFacture() != null || factureDetails==null) {
				return;
			}
			for (VenteFactureDetails venteFactureDetails : factureDetails) {
				venteFactureDetails.setVenteFacture(this);
			}
			this.factureDetails = factureDetails;
		}
}
