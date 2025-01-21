package perso.boulangerie.client.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
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

	public Vente save(Vente vente) {
		if (vente.getAdresseLivraison() == null) {
			vente.setDateLivree(vente.getDateVente());
			vente.setAdresseLivraison(vente.getClient().getAdresse());
		}
		vente.setEtat(1);
		return venteRepo.save(vente);
	}

	@Transactional
	public Vente validerVente(Integer idVente) {
		Vente vente = findVente(idVente);
		vente.setDateVente(LocalDateTime.now());
		vente.setEtat(2);
		return vente;
	}

	public List<Vente> getVentes() {
		return venteRepo.findAll(Sort.by(Sort.Direction.DESC, "id_vente"));
	}

	public Vente findVente(Integer id) {
		Vente v = venteRepo.findById(id).orElseThrow(() -> new RuntimeException("Vente not found with id: " + id));
		
		return v;
	}

	@Transactional
	public void deleteVente(Integer id) {
		venteFactureService.deleteByIdVente(id);
		venteRepo.deleteById(id);
	}
}
