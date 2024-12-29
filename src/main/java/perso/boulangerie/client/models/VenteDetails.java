package perso.boulangerie.client.models;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.Data;
import perso.boulangerie.production.models.Production;

@Data
@Entity
public class VenteDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetailsCommande;

    private Integer quantite;
    private BigDecimal prixUnitaire;

    @ManyToOne
    @JoinColumn(name = "idProduction")
    private Production production;

    @ManyToOne
    @JoinColumn(name = "idVente")
    private Vente vente;
}
