package perso.boulangerie.services.produit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.models.produit.Produit;
import perso.boulangerie.repositories.produit.ProduitRepo;
import perso.boulangerie.repositories.produit.ProduitsRecettesRepo;


@Service
public class ProduitService {
	@Autowired private ProduitRepo ProduitRepo;
	@Autowired private ProduitsRecettesRepo ProduitsRecettesRepo;

	public List<Produit> getProduits() {
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
