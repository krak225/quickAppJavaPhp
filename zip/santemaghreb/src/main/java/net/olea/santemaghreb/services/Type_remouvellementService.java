package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Type_remouvellement;

public interface Type_remouvellementService {

	List<Type_remouvellement> getType_remouvellements();
	Optional<Type_remouvellement> getType_remouvellement(Long id);
	Type_remouvellement addType_remouvellement(Type_remouvellement type_remouvellement);
	Type_remouvellement updateType_remouvellement(Long id, Type_remouvellement type_remouvellement);
	void deleteType_remouvellement(Long id);

}
