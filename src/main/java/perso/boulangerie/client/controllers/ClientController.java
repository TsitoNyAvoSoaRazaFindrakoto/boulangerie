package perso.boulangerie.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import perso.boulangerie.client.services.ClientService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import perso.boulangerie.client.models.Client;
import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientController {
	@Autowired
	private ClientService clientService;

	@GetMapping
	public String listClients(Model model) {
		List<Client> clients = clientService.getClients();
		model.addAttribute("clients", clients);
		return "clients/list";
	}

	@GetMapping("/new")
	public String showNewClientForm(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		return "clients/new";
	}

	@PostMapping
	public String saveClient(@ModelAttribute("client") Client client) {
		clientService.save(client);
		return "redirect:/clients";
	}

	@GetMapping("/edit/{id}")
	public String showEditClientForm(@PathVariable("id") Integer id, Model model) {
		Client client = clientService.findClient(id);
		model.addAttribute("client", client);
		return "clients/edit";
	}

	@PostMapping("/update/{id}")
	public String updateClient(@ModelAttribute("client") Client client) {
		clientService.save(client);
		return "redirect:/clients";
	}

	@GetMapping("/delete/{id}")
	public String deleteClient(@PathVariable("id") Integer id) {
		clientService.deleteClient(id);
		return "redirect:/clients";
	}
}
