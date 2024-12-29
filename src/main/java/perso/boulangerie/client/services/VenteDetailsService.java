package perso.boulangerie.client.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.client.models.VenteDetails;
import perso.boulangerie.client.repos.VenteDetailsRepo;

@Service
public class VenteDetailsService {
	@Autowired private VenteDetailsRepo venteDetailsRepo;

	public VenteDetails save(VenteDetails VenteDetails){
		return venteDetailsRepo.save(VenteDetails);
	}

	public List<VenteDetails> getVenteDetailss(){
		return venteDetailsRepo.findAll();
	}

	public VenteDetails findVenteDetails(Integer id){
		return venteDetailsRepo.findById(id).orElseThrow(() -> new RuntimeException("VenteDetails not found with id: " + id));
	}

	public void deleteVenteDetails(VenteDetails VenteDetails){
		venteDetailsRepo.delete(VenteDetails);
	}
}
