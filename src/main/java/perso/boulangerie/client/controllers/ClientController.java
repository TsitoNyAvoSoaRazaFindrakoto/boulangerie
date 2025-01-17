package perso.boulangerie.client.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import perso.boulangerie.client.models.Client;
import perso.boulangerie.client.models.Vente;
import perso.boulangerie.client.services.ClientService;

@AllArgsConstructor
@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @GetMapping
	public String getAllClient(Model model) {
		List<Client> clients = clientService.getClients();
		model.addAttribute("ventes", clients);
		return "client/client/list";
	}
    @GetMapping("/filter")
	public String getFilter(@RequestParam(required = false,name = "dateLivree") LocalDate dateLivree,@RequestParam(required = false,name = "dateVente") LocalDate dateVente,Model model) {
		List<Client> clients = clientService.filter(dateVente,dateLivree);
		model.addAttribute("ventes", clients);
		return "client/client/list";
	}
    
}
