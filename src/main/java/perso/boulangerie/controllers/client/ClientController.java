package perso.boulangerie.controllers.client;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import perso.boulangerie.models.client.Client;
import perso.boulangerie.services.client.ClientService;

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
	public String getFilter(@RequestParam(required = false) LocalDate startDateVente,
			@RequestParam(required = false) LocalDate endDateVente,
			@RequestParam(required = false) LocalDate startDateLivree,
			@RequestParam(required = false) LocalDate endDateLivree, Model model) {

		// Call service method to filter based on provided criteria
		List<Client> clients = clientService.filter(startDateVente, endDateVente, startDateLivree, endDateLivree);

		// Add filtered clients to the model
		model.addAttribute("ventes", clients);

		// Return the view
		return "client/client/list";
	}

	

}
