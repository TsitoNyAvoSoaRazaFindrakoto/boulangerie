package perso.boulangerie.services.produit;

import java.util.List;

import org.springframework.stereotype.Service;

import perso.boulangerie.models.produit.ProduitFormat;
import perso.boulangerie.repositories.produit.ProduitFormatRepo;


@Service
public class ProduitFormatService {

	private ProduitFormatRepo produitFormatRepo;

	public ProduitFormatService(ProduitFormatRepo produitFormatRepo) {
		this.produitFormatRepo = produitFormatRepo;
	}

	public ProduitFormat save(ProduitFormat produitFormat){
		produitFormat.setPrixUnitaire(produitFormat.getProduit().getPrixUnitaire().multiply(produitFormat.getFormat().getMultPrix()));
		return produitFormatRepo.save(produitFormat);
	}

	public List<ProduitFormat> getProduitFormats(){
		return produitFormatRepo.findAll();
	}

	public ProduitFormat getProduitFormat(Integer id) {
		return produitFormatRepo.findById(id).orElseThrow(() -> new RuntimeException("ProduitFormat not found with id: " + id));
	}

	public void deleteProduitFormat(Integer id) {
		produitFormatRepo.deleteById(id);
	}
}