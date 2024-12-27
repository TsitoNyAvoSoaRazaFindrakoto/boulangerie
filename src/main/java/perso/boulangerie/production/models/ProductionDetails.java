package perso.boulangerie.production.models;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;
import perso.boulangerie.produit.models.ProduitsRecettes;

@Data
@Entity
public class ProductionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProductionDetails;

    private BigDecimal quantite;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "idIngredient", referencedColumnName = "idIngredient"),
        @JoinColumn(name = "idProduit", referencedColumnName = "idProduit")
    })
    private ProduitsRecettes recettesProduit;

    @ManyToOne
    @JoinColumn(name = "idProduction")
    private Production production;
}
