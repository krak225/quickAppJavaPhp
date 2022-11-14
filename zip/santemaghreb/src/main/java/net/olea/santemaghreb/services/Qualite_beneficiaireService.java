package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Qualite_beneficiaire;

public interface Qualite_beneficiaireService {

	List<Qualite_beneficiaire> getQualite_beneficiaires();
	Optional<Qualite_beneficiaire> getQualite_beneficiaire(Long id);
	Qualite_beneficiaire addQualite_beneficiaire(Qualite_beneficiaire qualite_beneficiaire);
	Qualite_beneficiaire updateQualite_beneficiaire(Long id, Qualite_beneficiaire qualite_beneficiaire);
	void deleteQualite_beneficiaire(Long id);

}
