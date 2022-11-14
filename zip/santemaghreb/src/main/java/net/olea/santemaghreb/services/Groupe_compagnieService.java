package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Groupe_compagnie;

public interface Groupe_compagnieService {

	List<Groupe_compagnie> getGroupe_compagnies();
	Optional<Groupe_compagnie> getGroupe_compagnie(Long id);
	Groupe_compagnie addGroupe_compagnie(Groupe_compagnie groupe_compagnie);
	Groupe_compagnie updateGroupe_compagnie(Long id, Groupe_compagnie groupe_compagnie);
	void deleteGroupe_compagnie(Long id);

}
