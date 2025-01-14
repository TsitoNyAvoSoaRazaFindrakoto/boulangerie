package perso.boulangerie.client.services;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import perso.boulangerie.client.models.Vente;
import perso.boulangerie.client.repos.VenteRepo;

@Service
@AllArgsConstructor
public class VenteService {
	private VenteRepo venteRepo;
	private VenteFactureService venteFactureService;

	public Vente save(Vente Vente) {
		if (Vente.getAdresseLivraison() == null) {
			Vente.setDateLivree(Vente.getDateVente());
		}
		Vente.setEtat(1);
		return venteRepo.save(Vente);
	}

	@Transactional
	public Vente validerVente(Integer idVente) {
		Vente vente = findVente(idVente);
		vente.setEtat(2);
		return vente;
	}

	public List<Vente> getVentes() {
		return venteRepo.findAll();
	}

	public Vente findVente(Integer id) {
		Vente v = venteRepo.findById(id).orElseThrow(() -> new RuntimeException("Vente not found with id: " + id));
		v.setVenteDetails(venteFactureService.findByVente(v));
		return v;
	}

	@Transactional
	public void deleteVente(Integer id) {
		venteFactureService.deleteByIdVente(id);
		venteRepo.deleteById(id);
	}
}
