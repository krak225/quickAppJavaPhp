package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Motif;

public interface MotifService {

	List<Motif> getMotifs();
	Optional<Motif> getMotif(Long id);
	Motif addMotif(Motif motif);
	Motif updateMotif(Long id, Motif motif);
	void deleteMotif(Long id);

}
