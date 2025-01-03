package perso.boulangerie.produit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.produit.models.Produit;
import perso.boulangerie.produit.repos.ProduitRepo;
import perso.boulangerie.produit.repos.ProduitsRecettesRepo;

@Service
public class ProduitService {
	@Autowired private ProduitRepo ProduitRepo;
	@Autowired private ProduitsRecettesRepo ProduitsRecettesRepo;

	public List<Produit> getAllProduits() {
		return ProduitRepo.findAll();
	}

	public Produit getProduitById(Integer id) {
		Produit p = ProduitRepo.findById(id).orElseThrow(()-> new RuntimeException("Produit not found with id: "+id));
		p.setRecettes(ProduitsRecettesRepo.findByProduit(p));
		return p;
	}

	public Produit save(Produit Produit) {
		return ProduitRepo.save(Produit);
	}

	public void deleteProduit(Integer id) {
		ProduitRepo.deleteById(id);
	}

	public Produit saveWithRecette(Produit p){
		p.setIdProduit(save(p).getIdProduit());
		ProduitsRecettesRepo.saveAll(p.getRecettes());
		return p;
	}
}
