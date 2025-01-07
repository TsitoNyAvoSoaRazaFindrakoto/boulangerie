package perso.boulangerie.client.services;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import perso.boulangerie.client.models.Vente;
import perso.boulangerie.client.models.VenteFacture;
import perso.boulangerie.client.repos.VenteFactureRepo;

@Service
public class VenteFactureService {
	@Autowired
	private VenteFactureRepo venteFactureRepo;
	@Autowired
	private VenteFactureDetailsService venteFactureDetailsService;

	public List<VenteFacture> getVenteFactures() {
		return venteFactureRepo.findAll();
	}

	public VenteFacture getVenteFacture(Integer id) {
		VenteFacture vf = venteFactureRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("VenteFacture not found with id: " + id));
		vf.setFactureDetails(venteFactureDetailsService.findByFacture(vf));
		return vf;
	}

	@Transactional
	public VenteFacture save(VenteFacture venteFacture) {
		venteFacture.setPrixUnitaire(venteFacture.getProduitFormat().getPrixUnitaire());

		venteFacture.setIdVenteFacture(venteFactureRepo.save(venteFacture).getIdVenteFacture());
		venteFacture.setFactureDetails(venteFactureDetailsService.createForFacture(venteFacture, true));
		venteFactureDetailsService.saveAll(venteFacture.getFactureDetails());

		return venteFacture;
	}


	@Transactional
	public List<VenteFacture> saveAll(List<VenteFacture> venteFactures) {
		for (VenteFacture venteFacture : venteFactures) {
			save(venteFacture);
		}
		return findByVente(venteFactures.get(0).getVente());
	}

	public List<VenteFacture> findByVente(Vente vente) {
		return venteFactureRepo.findByVente(vente);
	}

	public void deleteFactureVente(Integer id) {
		venteFactureRepo.deleteById(id);
	}
}
