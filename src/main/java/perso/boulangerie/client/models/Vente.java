package perso.boulangerie.client.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import perso.boulangerie.employe.models.Employe;

@Data
@Entity
public class Vente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVente;

	private LocalDateTime dateVente;
	private LocalDateTime dateLivree;
	private String adresseLivraison;
	private BigDecimal montant;

	@ManyToOne
	@JoinColumn(name = "id_Employe")
	private Employe employe;

	@ManyToOne
	@JoinColumn(name = "idClient")
	private Client client;

	private Integer Etat;

	@OneToMany(mappedBy = "vente", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<VenteFacture> venteDetails;

	public void setVenteDetails(List<VenteFacture> venteFactures) {
		if (venteFactures != null && !venteFactures.isEmpty() && venteFactures.get(0).getVente() != null
				|| venteFactures == null) {
			return;
		}
		for (VenteFacture venteFacture : venteFactures) {
			venteFacture.setVente(this);
		}
		this.venteDetails = venteFactures;
	}
}
