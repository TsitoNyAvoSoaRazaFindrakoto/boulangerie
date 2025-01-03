package perso.boulangerie.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import perso.boulangerie.client.models.Vente;
import perso.boulangerie.client.models.VenteFacture;
import perso.boulangerie.client.services.VenteService;

import java.util.List;

@RestController
@RequestMapping("/ventes")
public class VenteController {

    @Autowired
    private VenteService venteService;

    @GetMapping
    public List<Vente> getAllVentes() {
        return venteService.getVentes();
    }

    @GetMapping("/{id}")
    public Vente getVenteById(@PathVariable Integer id) {
        return venteService.findVente(id);
    }

    @PostMapping
    public Vente createVente(@RequestParam Vente vente, @RequestParam List<VenteFacture> venteDetails) {
        return venteService.saveWithDetails(vente, venteDetails);
    }

    @PutMapping("/{id}")
    public Vente updateVente(@RequestParam Vente vente, @RequestParam List<VenteFacture> venteDetails) {
        return venteService.saveWithDetails(vente,venteDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteVente(@PathVariable Integer id) {
        venteService.deleteVente(id);
    }
}
