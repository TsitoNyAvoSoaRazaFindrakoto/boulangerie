package perso.boulangerie.services.client;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import perso.boulangerie.models.client.Vente;
import perso.boulangerie.models.client.VenteFacture;
import perso.boulangerie.repositories.client.VenteFactureRepo;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


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
		return vf;
	}

	@Transactional
	public VenteFacture save(VenteFacture venteFacture) {
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

	@Transactional
	public Integer deleteFactureVente(Integer id) {
		Integer idVente = getVenteFacture(id).getVente().getIdVente();
		venteFactureDetailsService.deleteByIdVenteFacture(id);
		venteFactureRepo.deleteById(id);
		return idVente;
	}

	@Transactional
	public void deleteByIdVente(Integer idVente) {
		venteFactureDetailsService.deleteByIdVente(idVente);
		venteFactureRepo.deleteByIdVente(idVente);
	}

	public List<VenteFacture> getProduit(Integer idFormat, Integer IdProduit) {
		if (idFormat == null && IdProduit == null) {
			return getVenteFactures();
		}
		if (idFormat == null) {
			return venteFactureRepo.findByProduit(IdProduit);
		}
		if (IdProduit == null) {
			return venteFactureRepo.findByFormat(idFormat);

		}
		return venteFactureRepo.findByProduitFormat(IdProduit, idFormat);
	}

	public List<VenteFacture> getCategorie(Integer idFormat, Integer IdCategorie) {
		if (idFormat == null && IdCategorie == null) {
			return getVenteFactures();

		}
		if (idFormat == null) {
			return venteFactureRepo.findByProduit(IdCategorie);
		}
		if (IdCategorie == null) {
			return venteFactureRepo.findByFormat(idFormat);

		}
		return venteFactureRepo.findByCategorieFormat(IdCategorie, idFormat);
	}
}
