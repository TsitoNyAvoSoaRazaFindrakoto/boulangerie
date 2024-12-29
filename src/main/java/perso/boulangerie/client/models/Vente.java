package perso.boulangerie.client.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVente;

    private LocalDateTime dateVente;
    private LocalDateTime dateLivree;
    private String adresseLivraison;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;
}
