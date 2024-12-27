package perso.boulangerie.fournisseur.models;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;
import perso.boulangerie.fournisseur.id.IngredientsFournisseursId;
import perso.boulangerie.produit.models.Ingredient;

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
