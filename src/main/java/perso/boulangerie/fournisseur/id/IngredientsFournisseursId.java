package perso.boulangerie.fournisseur.id;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientsFournisseursId implements Serializable {
    private Integer fournisseur;
    private Integer ingredient;

    // equals and hashCode methods


}
