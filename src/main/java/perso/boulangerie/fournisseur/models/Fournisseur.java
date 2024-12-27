package perso.boulangerie.fournisseur.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFournisseur;

    private String nom;
    private String contact;
}