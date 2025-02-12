package perso.boulangerie.models.employe;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	private BigDecimal commission;
	
	@ManyToOne
	@JoinColumn(name = "idSexe")
	private Sexe sexe;


}
