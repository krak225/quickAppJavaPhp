package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Mouvement_motif;

public interface Mouvement_motifService {

	List<Mouvement_motif> getMouvement_motifs();
	Optional<Mouvement_motif> getMouvement_motif(Long id);
	Mouvement_motif addMouvement_motif(Mouvement_motif mouvement_motif);
	Mouvement_motif updateMouvement_motif(Long id, Mouvement_motif mouvement_motif);
	void deleteMouvement_motif(Long id);

}
