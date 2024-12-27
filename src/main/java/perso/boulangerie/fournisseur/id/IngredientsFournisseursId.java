package perso.boulangerie.fournisseur.id;


import java.io.Serializable;
import lombok.Data;

@Data
public class IngredientsFournisseursId implements Serializable {
    private Integer fournisseur;
    private Integer ingredient;

    // equals and hashCode methods
}
