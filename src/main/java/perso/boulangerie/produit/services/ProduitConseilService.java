package perso.boulangerie.produit.services;

import java.time.YearMonth;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import perso.boulangerie.produit.models.ProduitConseil;
import perso.boulangerie.produit.repos.ProduitConseilRepo;

@Service
@AllArgsConstructor
public class ProduitConseilService {
	private final ProduitConseilRepo produitConseilRepo;

	public ProduitConseil save(ProduitConseil pc) {
		return produitConseilRepo.save(pc);
	}

	public List<ProduitConseil> getForYearMonth(Integer mois, Integer annee) {
		if (mois == null) {
			return produitConseilRepo.findAllForCurrentMonth();
		}
		if (mois == 0 ) {
			return produitConseilRepo.findAllByYear(annee);
		} else {
			return produitConseilRepo.findAllByYearMonth(YearMonth.of(annee, mois));
		}
	}
}
