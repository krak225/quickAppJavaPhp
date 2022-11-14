package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Fractionnement;

public interface FractionnementService {

	List<Fractionnement> getFractionnements();
	Optional<Fractionnement> getFractionnement(Long id);
	Fractionnement addFractionnement(Fractionnement fractionnement);
	Fractionnement updateFractionnement(Long id, Fractionnement fractionnement);
	void deleteFractionnement(Long id);

}
