package perso.boulangerie.client.models;

import java.time.LocalDateTime;
import java.util.List;

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
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVente;

    private LocalDateTime dateVente;
    private LocalDateTime dateLivree;
    private String adresseLivraison;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;

		private Integer Etat;

		@Transient
		private List<VenteFacture> venteDetails;

		public void setVenteDetails(List<VenteFacture> venteFactures){
			if (venteFactures != null && !venteFactures.isEmpty() && venteFactures.get(0).getVente() != null || venteFactures==null) {
				return;
			}
			for (VenteFacture venteFacture : venteFactures) {
				venteFacture.setVente(this);
			}
			this.venteDetails = venteFactures;
		}
}
