package perso.boulangerie.employe.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import perso.boulangerie.employe.models.Employe;
import perso.boulangerie.employe.repos.EmployeRepo;

@Service
@AllArgsConstructor
public class EmployeService {
	private final EmployeRepo employeRepo;

	// Create
	public Employe createEmploye(Employe employe) {
		return employeRepo.save(employe);
	}

	// Read All
	public List<Employe> findAllEmployes() {
		return employeRepo.findAll();
	}

	// Read By ID
	public Optional<Employe> findEmployeById(Integer id) {
		return employeRepo.findById(id);
	}

	// Update
	public Employe updateEmploye(Integer id, Employe employeDetails) {
		return employeRepo.findById(id).map(employe -> {
			employe.setNom(employeDetails.getNom());
			employe.setPrenoms(employeDetails.getPrenoms());
			employe.setDateNaissance(employeDetails.getDateNaissance());
			employe.setDateEmbauche(employeDetails.getDateEmbauche());
			employe.setIdTypeEmploye(employeDetails.getIdTypeEmploye());
			employe.setEstEmploye(employeDetails.isEstEmploye());
			return employeRepo.save(employe);
		}).orElseThrow(() -> new RuntimeException("Employe not found with id " + id));
	}

	// Delete
	public void deleteEmploye(Integer id) {
		if (employeRepo.existsById(id)) {
			employeRepo.deleteById(id);
		} else {
			throw new RuntimeException("Employe not found with id " + id);
		}
	}

	// Custom query: Find all vendors
	public List<Employe> findAllVendeurs() {
		return employeRepo.findAllVendeursWithComission();
	}

	// Custom query: Find vendeurs by criteria
	public List<Employe> findAllVendeursByCriteria(BigDecimal minCommission, BigDecimal maxCommission,
			LocalDate minPeriod, LocalDate maxPeriod) {
		String minP = minPeriod == null ? null : minPeriod.toString();
		String maxP = maxPeriod == null ? null : maxPeriod.toString();
		return employeRepo.findAllVendeursByCriteria(minCommission, maxCommission, minP, maxP);
	}

	public HashMap<String, BigDecimal[]> filterListBySexe(List<Employe> employes) {
		HashMap<String, BigDecimal[]> filtre = new HashMap<>();

		// Initialize with default values for both genders
		filtre.put("Masculin", new BigDecimal[] { BigDecimal.ZERO, BigDecimal.ZERO });
		filtre.put("Feminin", new BigDecimal[] { BigDecimal.ZERO, BigDecimal.ZERO });

		for (Employe employe : employes) {
			if (employe.getSexe() != null) {
				String genre = employe.getSexe().getGenre();

				// Handle potential null commission
				BigDecimal commission = employe.getCommission() != null ? employe.getCommission() : BigDecimal.ZERO;

				if (filtre.containsKey(genre)) {
					// Get current values
					BigDecimal count = filtre.get(genre)[0];
					BigDecimal totalCommission = filtre.get(genre)[1];

					// Update values
					filtre.get(genre)[0] = count.add(BigDecimal.ONE); // Increment count
					filtre.get(genre)[1] = totalCommission.add(commission); // Add commission
				}
			}
		}
		return filtre;
	}
}
