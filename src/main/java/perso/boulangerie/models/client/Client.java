package perso.boulangerie.models.client;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;

    private String nom;
    private String adresse;
}
