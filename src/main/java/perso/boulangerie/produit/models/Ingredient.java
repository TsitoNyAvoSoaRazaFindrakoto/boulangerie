package perso.boulangerie.produit.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngredient;

    private String nom;

    @ManyToOne
    @JoinColumn(name = "idUnite")
    private Unite unite;
}