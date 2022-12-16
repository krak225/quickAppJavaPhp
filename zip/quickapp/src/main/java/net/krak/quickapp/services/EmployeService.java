package net.krak.quickapp.services;

import java.util.List;
import java.util.Optional;

import net.krak.quickapp.entities.Employe;

public interface EmployeService {

	List<Employe> getEmployes();
	Optional<Employe> getEmploye(Long id);
	Employe addEmploye(Employe employe);
	Employe updateEmploye(Long id, Employe employe);
	void deleteEmploye(Long id);

}
