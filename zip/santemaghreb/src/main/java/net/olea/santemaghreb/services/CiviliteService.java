package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Civilite;

public interface CiviliteService {

	List<Civilite> getCivilites();
	Optional<Civilite> getCivilite(Long id);
	Civilite addCivilite(Civilite civilite);
	Civilite updateCivilite(Long id, Civilite civilite);
	void deleteCivilite(Long id);

}
