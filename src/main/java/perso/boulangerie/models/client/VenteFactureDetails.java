package perso.boulangerie.models.client;

import jakarta.persistence.*;
import lombok.Data;
import perso.boulangerie.models.production.Production;

@Data
@Entity
public class VenteFactureDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenteFactureDetails;

    private Integer quantite;

    @ManyToOne
    @JoinColumn(name = "idProduction")
    private Production production;

    @ManyToOne
    @JoinColumn(name = "idVenteFacture")
    private VenteFacture venteFacture;
}
