package perso.boulangerie.fournisseur.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.fournisseur.models.Fournisseur;
import perso.boulangerie.fournisseur.repos.FournisseurRepo;

@Service
public class FournisseurService {
	
	FournisseurRepo fournisseurRepo;

	public FournisseurService(FournisseurRepo fournisseurService) {
		this.fournisseurRepo = fournisseurService;
	}

	public Fournisseur save(Fournisseur fournisseur) {
		return fournisseurRepo.save(fournisseur);
	}
}
