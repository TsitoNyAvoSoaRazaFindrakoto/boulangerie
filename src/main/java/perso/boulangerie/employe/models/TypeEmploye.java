package perso.boulangerie.employe.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class TypeEmploye {
	@Id
	private String idTypeEmploye;

	private String nom, description;
}
