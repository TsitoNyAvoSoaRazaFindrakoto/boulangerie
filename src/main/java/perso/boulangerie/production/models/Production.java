package perso.boulangerie.production.models;

import jakarta.persistence.*;
import lombok.Data;
import perso.boulangerie.produit.models.Produit;

import java.time.LocalDateTime;

@Data
@Entity
public class Production {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduction;

    private LocalDateTime dateProduction;
    private Integer quantite;

    @ManyToOne
    @JoinColumn(name = "idProduit")
    private Produit produit;
}
