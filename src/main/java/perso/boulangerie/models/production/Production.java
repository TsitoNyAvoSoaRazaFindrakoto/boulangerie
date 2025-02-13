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

	private LocalDateTime dateProduction = LocalDateTime.now();
	private Integer quantite = 0;

	@ManyToOne
	@JoinColumn(name = "idProduitFormat")
	private ProduitFormat produitFormat;

	@OneToMany(mappedBy = "production", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ProductionDetails> productionDetails;

	public void setProductionDetails(List<ProductionDetails> productionDetailslist) {
		this.productionDetails.clear();
		if (productionDetailslist != null) {
			for (ProductionDetails details : productionDetailslist) {
				details.setProduction(this);
			}
			this.productionDetails = productionDetailslist;
		}
	}
}
