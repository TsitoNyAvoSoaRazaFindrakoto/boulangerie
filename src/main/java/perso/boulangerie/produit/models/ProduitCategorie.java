package perso.boulangerie.produit.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Produit_Categorie")
public class ProduitCategorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Produit_Categorie")
    private Integer idProduitCategorie;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "description", length = 50)
    private String description;

    // Default constructor
    public ProduitCategorie() {
    }

    // Constructor with parameters
    public ProduitCategorie(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
}
