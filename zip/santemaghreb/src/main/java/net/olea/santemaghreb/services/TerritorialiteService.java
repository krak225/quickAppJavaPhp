package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Territorialite;

public interface TerritorialiteService {

	List<Territorialite> getTerritorialites();
	Optional<Territorialite> getTerritorialite(Long id);
	Territorialite addTerritorialite(Territorialite territorialite);
	Territorialite updateTerritorialite(Long id, Territorialite territorialite);
	void deleteTerritorialite(Long id);

}
