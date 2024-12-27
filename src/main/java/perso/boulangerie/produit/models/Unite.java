package perso.boulangerie.produit.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Unite {
    @Id
		// id_unite
    private String idUnite;

    private String val;
}
