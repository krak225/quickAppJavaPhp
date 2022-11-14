package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Type_client;

public interface Type_clientService {

	List<Type_client> getType_clients();
	Optional<Type_client> getType_client(Long id);
	Type_client addType_client(Type_client type_client);
	Type_client updateType_client(Long id, Type_client type_client);
	void deleteType_client(Long id);

}
