package perso.boulangerie.client.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.client.models.Vente;
import perso.boulangerie.client.repos.VenteRepo;

@Service
public class VenteService {
	@Autowired private VenteRepo venteRepo;

	public Vente save(Vente Vente){
		return venteRepo.save(Vente);
	}

	public List<Vente> getVentes(){
		return venteRepo.findAll();
	}

	public Vente findVente(Integer id){
		return venteRepo.findById(id).orElseThrow(() -> new RuntimeException("Vente not found with id: " + id));
	}

	public void deleteVente(Vente Vente){
		venteRepo.delete(Vente);
	}
}
