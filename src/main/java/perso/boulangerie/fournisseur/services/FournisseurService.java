package perso.boulangerie.fournisseur.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.fournisseur.models.Fournisseur;
import perso.boulangerie.fournisseur.repos.FournisseurRepo;

@Service
public class FournisseurService {

	private FournisseurRepo FournisseurRepo;

	public FournisseurService(perso.boulangerie.fournisseur.repos.FournisseurRepo fournisseurRepo) {
		FournisseurRepo = fournisseurRepo;
	}

	public Fournisseur save(Fournisseur Fournisseur) {
		return FournisseurRepo.save(Fournisseur);
	}

	public List<Fournisseur> getFournisseurs() {
		return FournisseurRepo.findAll();
	}

	public Fournisseur findFournisseur(Integer id) {
		return FournisseurRepo.findById(id).orElseThrow(() -> new RuntimeException("Fournisseur not found with id: " + id));
	}

	public void deleteFournisseur(Fournisseur Fournisseur) {
		FournisseurRepo.delete(Fournisseur);
	}
}
