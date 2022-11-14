package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Mouvement;

public interface MouvementService {

	List<Mouvement> getMouvements();
	Optional<Mouvement> getMouvement(Long id);
	Mouvement addMouvement(Mouvement mouvement);
	Mouvement updateMouvement(Long id, Mouvement mouvement);
	void deleteMouvement(Long id);

}
