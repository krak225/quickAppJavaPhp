package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Type_regulation;

public interface Type_regulationService {

	List<Type_regulation> getType_regulations();
	Optional<Type_regulation> getType_regulation(Long id);
	Type_regulation addType_regulation(Type_regulation type_regulation);
	Type_regulation updateType_regulation(Long id, Type_regulation type_regulation);
	void deleteType_regulation(Long id);

}
