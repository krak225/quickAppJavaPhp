package net.krak.quickapp.services;

import java.util.List;
import java.util.Optional;

import net.krak.quickapp.entities.Compte;

public interface CompteService {

	List<Compte> getComptes();
	Optional Compte getCompte(Long id);
	Compte AddCompte(Compte compte);
	Compte EditCompte(Long id, Compte compte);
	void Compte deleteCompte(Long id);

}
