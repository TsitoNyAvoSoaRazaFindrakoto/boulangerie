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
	public String getFilter(@RequestParam(required = false, name = "startDateVente") LocalDate startDateVente,
			@RequestParam(required = false, name = "endDateVente") LocalDate endDateVente,
			@RequestParam(required = false, name = "startDateLivree") LocalDate startDateLivree,
			@RequestParam(required = false, name = "endDateLivree") LocalDate endDateLivree, Model model) {

		// Call service method to filter based on provided criteria
		List<Client> clients = clientService.filter(startDateVente, endDateVente, startDateLivree, endDateLivree);

		// Add filtered clients to the model
		model.addAttribute("ventes", clients);

		// Return the view
		return "client/client/list";
	}

	

}
