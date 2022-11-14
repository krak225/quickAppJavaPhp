package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Pays;

public interface PaysService {

	List<Pays> getPayss();
	Optional<Pays> getPays(Long id);
	Pays addPays(Pays pays);
	Pays updatePays(Long id, Pays pays);
	void deletePays(Long id);

}
