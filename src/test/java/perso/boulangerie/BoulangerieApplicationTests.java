package perso.boulangerie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import perso.boulangerie.models.production.Production;
import perso.boulangerie.models.production.ProductionDetails;
import perso.boulangerie.models.produit.ProduitFormat;
import perso.boulangerie.repositories.produit.ProduitFormatRepo;
import perso.boulangerie.services.production.ProductionDetailsService;
import perso.boulangerie.services.production.ProductionService;

@SpringBootTest
class BoulangerieApplicationTests {

	@Autowired
	ProductionDetailsService productionDetailsService;
	@Autowired
	ProduitFormatRepo produitFormatRepo;

	@Test
	void contextLoads() {

	}

	@Test
	void testProduction() {
		Production p = new Production();
		
		
		p.setQuantite(10);
		p.setDateProduction(LocalDateTime.now());
		p.setProduitFormat(produitFormatRepo.findById(1).get());

		List<ProductionDetails> pds = productionDetailsService.createForProduction(p, true);

		assertEquals(4, pds.size());
	}
}
