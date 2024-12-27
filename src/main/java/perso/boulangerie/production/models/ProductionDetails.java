package perso.boulangerie.production.models;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;
import perso.boulangerie.fournisseur.models.IngredientEntree;

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
    private Production production;
}
