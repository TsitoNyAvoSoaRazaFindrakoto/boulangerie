package perso.boulangerie.produit.models;

import java.time.LocalDate;
import java.time.YearMonth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class ProduitConseil {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProduitConseil;
	private LocalDate dateDebut, dateFin;

	@Transient
	private String moisAnnee;

	@ManyToOne
	@JoinColumn(name = "idProduitFormat")
	private ProduitFormat produit;

	public void setMoisAnnee(String anneeMois) {
		YearMonth ym = YearMonth.parse(anneeMois);
		setDateDebut(ym.atDay(1));
		setDateFin(ym.atEndOfMonth());
	}
}
