package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Type_mouvement;

public interface Type_mouvementService {

	List<Type_mouvement> getType_mouvements();
	Optional<Type_mouvement> getType_mouvement(Long id);
	Type_mouvement addType_mouvement(Type_mouvement type_mouvement);
	Type_mouvement updateType_mouvement(Long id, Type_mouvement type_mouvement);
	void deleteType_mouvement(Long id);

}
