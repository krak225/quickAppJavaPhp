package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Situation_matrimoniale;

public interface Situation_matrimonialeService {

	List<Situation_matrimoniale> getSituation_matrimoniales();
	Optional<Situation_matrimoniale> getSituation_matrimoniale(Long id);
	Situation_matrimoniale addSituation_matrimoniale(Situation_matrimoniale situation_matrimoniale);
	Situation_matrimoniale updateSituation_matrimoniale(Long id, Situation_matrimoniale situation_matrimoniale);
	void deleteSituation_matrimoniale(Long id);

}
