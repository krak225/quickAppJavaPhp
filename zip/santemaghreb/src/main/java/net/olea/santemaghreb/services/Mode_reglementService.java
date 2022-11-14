package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Mode_reglement;

public interface Mode_reglementService {

	List<Mode_reglement> getMode_reglements();
	Optional<Mode_reglement> getMode_reglement(Long id);
	Mode_reglement addMode_reglement(Mode_reglement mode_reglement);
	Mode_reglement updateMode_reglement(Long id, Mode_reglement mode_reglement);
	void deleteMode_reglement(Long id);

}
