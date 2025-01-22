package perso.boulangerie.employe.services;

import java.math.BigDecimal;
import java.time.LocalDate;
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
}
