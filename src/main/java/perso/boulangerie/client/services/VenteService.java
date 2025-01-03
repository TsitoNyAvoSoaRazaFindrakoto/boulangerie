package perso.boulangerie.client.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import perso.boulangerie.client.models.Vente;
import perso.boulangerie.client.models.VenteFacture;
import perso.boulangerie.client.repos.VenteRepo;

@Service
public class VenteService {
	@Autowired private VenteRepo venteRepo;
	@Autowired private VenteFactureService venteFactureService;

	public Vente save(Vente Vente){
		if (Vente.getAdresseLivraison() == null) {
			Vente.setDateLivree(Vente.getDateVente());
			Vente.setEtat(1);
		}
		return venteRepo.save(Vente);
	}

	@Transactional
	public Vente saveWithDetails(Vente Vente,List<VenteFacture> venteDetails){
		Vente.setIdVente(save(Vente).getIdVente());
		Vente.setVenteDetails(venteDetails);
		venteFactureService.saveAll(Vente.getVenteDetails());

		return Vente;
	}

	public List<Vente> getVentes(){
		return venteRepo.findAll();
	}

	public Vente findVente(Integer id){
		Vente v = venteRepo.findById(id).orElseThrow(() -> new RuntimeException("Vente not found with id: " + id));
		v.setVenteDetails(venteFactureService.findByVente(v));
		return v;
	}

	public void deleteVente(Vente Vente){
		venteRepo.delete(Vente);
	}
}
