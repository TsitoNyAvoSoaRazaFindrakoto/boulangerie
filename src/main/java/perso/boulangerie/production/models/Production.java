package perso.boulangerie.production.models;

import jakarta.persistence.*;
import lombok.Data;
import perso.boulangerie.produit.models.Produit;

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
    @JoinColumn(name = "idProduit")
    private Produit produit;

		@Transient
		private List<ProductionDetails> productionDetails;

		public void setProductionDetails(List<ProductionDetails> productionDetails){
			this.productionDetails = productionDetails;
			if (productionDetails != null) {
				for (ProductionDetails details : productionDetails) {
					details.setProduction(this);
				}
			}
		}
}
