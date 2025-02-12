package perso.boulangerie.services.fournisseur;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import perso.boulangerie.models.fournisseur.Fournisseur;
import perso.boulangerie.repositories.fournisseur.FournisseurRepo;

@Service
public class FournisseurService {

	private FournisseurRepo fournisseurRepo;

	public FournisseurService(FournisseurRepo fournisseurRepo) {
		this.fournisseurRepo = fournisseurRepo;
	}

	@Transactional
	public Fournisseur save(Fournisseur Fournisseur) {
		return fournisseurRepo.save(Fournisseur);
	}

	public List<Fournisseur> getFournisseursContractants() {
		return fournisseurRepo.findByEtat(true);
	} 


	public List<Fournisseur> getFournisseurs() {
		return fournisseurRepo.findAll();
	}

	public Fournisseur findFournisseur(Integer id) {
		return fournisseurRepo.findById(id).orElseThrow(() -> new RuntimeException("Fournisseur not found with id: " + id));
	}

	@Transactional
	public void rompreContrat(Integer idFournisseur) {
		fournisseurRepo.rompreContrat(idFournisseur);
	}
}
