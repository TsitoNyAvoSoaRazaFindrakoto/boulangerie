package perso.boulangerie.produit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.produit.models.Produit;
import perso.boulangerie.produit.repos.ProduitRepo;

@Service
public class ProduitService {
	@Autowired
	private ProduitRepo ProduitRepo;

	public List<Produit> getAllProduits() {
		return ProduitRepo.findAll();
	}

	public Produit getProduitById(Integer id) {
		return ProduitRepo.findById(id).orElseThrow(()-> new RuntimeException("Produit not found with id: "+id));
	}

	public Produit save(Produit Produit) {
		return ProduitRepo.save(Produit);
	}

	public void deleteProduit(Integer id) {
		ProduitRepo.deleteById(id);
	}
}
