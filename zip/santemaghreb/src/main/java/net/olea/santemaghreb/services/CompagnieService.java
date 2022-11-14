package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Compagnie;

public interface CompagnieService {

	List<Compagnie> getCompagnies();
	Optional<Compagnie> getCompagnie(Long id);
	Compagnie addCompagnie(Compagnie compagnie);
	Compagnie updateCompagnie(Long id, Compagnie compagnie);
	void deleteCompagnie(Long id);

}
