package perso.boulangerie.models.production;

import jakarta.persistence.*;
import lombok.Data;
import perso.boulangerie.models.produit.ProduitFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Production {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProduction;

	private LocalDateTime dateProduction;
	private Integer quantite;

	@ManyToOne
	@JoinColumn(name = "idProduitFormat")
	private ProduitFormat produitFormat;

	@OneToMany(mappedBy = "production", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ProductionDetails> productionDetails;

	public void setProductionDetails(List<ProductionDetails> productionDetailslist) {
		if (productionDetailslist != null && !productionDetailslist.isEmpty()
				&& productionDetailslist.get(0).getProduction() != null || productionDetailslist == null) {
			return;
		}
		if (productionDetailslist != null) {
			for (ProductionDetails details : productionDetailslist) {
				details.setProduction(this);
			}
		}
		this.productionDetails = productionDetailslist;
	}
}
