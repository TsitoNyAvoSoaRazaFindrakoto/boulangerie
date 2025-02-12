package perso.boulangerie.models.production;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import perso.boulangerie.models.fournisseur.IngredientEntree;

@Data
@Entity
public class ProductionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProductionDetails;

    private BigDecimal quantite;

    @ManyToOne
    @JoinColumn(name = "IdIngredientEntree")
    private IngredientEntree ingredientEntree;

    @ManyToOne
    @JoinColumn(name = "IdProduction")
		@JsonBackReference
    private Production production;
}
