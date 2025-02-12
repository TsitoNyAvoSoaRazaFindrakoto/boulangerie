package perso.boulangerie.services.client;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.models.client.Client;
import perso.boulangerie.repositories.client.ClientRepo;


@Service
public class ClientService {
	@Autowired
	private ClientRepo clientRepo;

	public Client save(Client client) {
		return clientRepo.save(client);
	}

	public List<Client> getClients() {
		return clientRepo.findAll();
	}

	public Client findClient(Integer id) {
		return clientRepo.findById(id).orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
	}

	public void deleteClient(Integer id) {
		clientRepo.deleteById(id);
	}

	public List<Client> filter(LocalDate startDateVente, LocalDate endDateVente, LocalDate startDateLivree,
			LocalDate endDateLivree) {
		String startVente = (startDateVente != null) ? startDateVente.toString() : null;
		String endVente = (endDateVente != null) ? endDateVente.toString() : null;
		String startLivree = (startDateLivree != null) ? startDateLivree.toString() : null;
		String endLivree = (endDateLivree != null) ? endDateLivree.toString() : null;

		return clientRepo.findClientsByVenteAndLivraisonIntervals(startVente, endVente, startLivree, endLivree);
	}

}
