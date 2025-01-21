package perso.boulangerie.employe.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Employe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEmploye;

	private String nom,prenoms,idTypeEmploye;
	private LocalDate dateNaissance,dateEmbauche;
	private boolean estEmploye;

	@Transient
	private BigDecimal commission;

}
