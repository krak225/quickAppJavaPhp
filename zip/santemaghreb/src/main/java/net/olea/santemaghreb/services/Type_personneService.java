package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Type_personne;

public interface Type_personneService {

	List<Type_personne> getType_personnes();
	Optional<Type_personne> getType_personne(Long id);
	Type_personne addType_personne(Type_personne type_personne);
	Type_personne updateType_personne(Long id, Type_personne type_personne);
	void deleteType_personne(Long id);

}
