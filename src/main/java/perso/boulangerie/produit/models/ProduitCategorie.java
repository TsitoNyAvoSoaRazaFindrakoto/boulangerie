package perso.boulangerie.produit.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class ProduitCategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduitCategorie;

    private String nom;
    private String description;
}