package perso.boulangerie.client.models;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import perso.boulangerie.produit.models.ProduitFormat;

@Data
@Entity
public class VenteFacture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVenteFacture;

	private Integer quantite;
	private BigDecimal prixUnitaire, montant;

	@ManyToOne
	@JoinColumn(name = "idProduitFormat")
	private ProduitFormat produitFormat;

	@ManyToOne
	@JoinColumn(name = "idVente")
	@JsonBackReference
	private Vente vente;

	@OneToMany(mappedBy = "venteFacture", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<VenteFactureDetails> factureDetails;

	public void setFactureDetails(List<VenteFactureDetails> factureDetails) {
		if (factureDetails != null && !factureDetails.isEmpty() && factureDetails.get(0).getVenteFacture() != null
				|| factureDetails == null) {
			return;
		}
		for (VenteFactureDetails venteFactureDetails : factureDetails) {
			venteFactureDetails.setVenteFacture(this);
		}
		this.factureDetails = factureDetails;
	}
}
