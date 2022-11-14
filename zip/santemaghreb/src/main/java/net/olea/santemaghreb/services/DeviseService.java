package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Devise;

public interface DeviseService {

	List<Devise> getDevises();
	Optional<Devise> getDevise(Long id);
	Devise addDevise(Devise devise);
	Devise updateDevise(Long id, Devise devise);
	void deleteDevise(Long id);

}
