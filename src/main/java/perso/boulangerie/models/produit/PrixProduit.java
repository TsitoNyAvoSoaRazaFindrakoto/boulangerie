package perso.boulangerie.models.produit;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Entity
public class PrixProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrixProduit;

    @Column(nullable = false)
    private LocalDate changement;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "idProduit")
    private Produit produit;
}

