package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Type_connexite;

public interface Type_connexiteService {

	List<Type_connexite> getType_connexites();
	Optional<Type_connexite> getType_connexite(Long id);
	Type_connexite addType_connexite(Type_connexite type_connexite);
	Type_connexite updateType_connexite(Long id, Type_connexite type_connexite);
	void deleteType_connexite(Long id);

}
