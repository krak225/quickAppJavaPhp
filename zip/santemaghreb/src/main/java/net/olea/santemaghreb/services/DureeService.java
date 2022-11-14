package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Duree;

public interface DureeService {

	List<Duree> getDurees();
	Optional<Duree> getDuree(Long id);
	Duree addDuree(Duree duree);
	Duree updateDuree(Long id, Duree duree);
	void deleteDuree(Long id);

}
