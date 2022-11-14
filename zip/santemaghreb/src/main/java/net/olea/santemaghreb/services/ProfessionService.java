package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Profession;

public interface ProfessionService {

	List<Profession> getProfessions();
	Optional<Profession> getProfession(Long id);
	Profession addProfession(Profession profession);
	Profession updateProfession(Long id, Profession profession);
	void deleteProfession(Long id);

}
