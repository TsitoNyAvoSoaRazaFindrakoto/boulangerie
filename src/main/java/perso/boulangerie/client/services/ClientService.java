package perso.boulangerie.client.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import perso.boulangerie.client.models.Client;
import perso.boulangerie.client.repos.ClientRepo;

@Service
public class ClientService {
	@Autowired private ClientRepo clientRepo;

	public Client save(Client client){
		return clientRepo.save(client);
	}

	public List<Client> getClients(){
		return clientRepo.findAll();
	}

	public Client findClient(Integer id){
		return clientRepo.findById(id).orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
	}

	public void deleteClient(Client client){
		clientRepo.delete(client);
	}
}
