package perso.boulangerie.models.employe;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Sexe {
	@Id
	private String idSexe;
	private String genre;
}
