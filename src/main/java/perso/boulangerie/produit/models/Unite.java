package perso.boulangerie.produit.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Unite {
    @Id
    private String idUnite;

    private String val;
}
