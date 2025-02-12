package perso.boulangerie.models.fournisseur;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;
import perso.boulangerie.models.fournisseur.id.IngredientsFournisseursId;
import perso.boulangerie.models.produit.Ingredient;

@Data
@Entity
@IdClass(IngredientsFournisseursId.class)
public class IngredientsFournisseurs {
    @Id
    @ManyToOne
    @JoinColumn(name = "idFournisseur")
    private Fournisseur fournisseur;

    @Id
    @ManyToOne
    @JoinColumn(name = "idIngredient")
    private Ingredient ingredient;

    private BigDecimal prixUnitaire;
}
