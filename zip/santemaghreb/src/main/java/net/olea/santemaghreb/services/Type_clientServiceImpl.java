package net.olea.santemaghreb.services;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Statut;

import net.olea.santemaghreb.entities.Type_client;

import net.olea.santemaghreb.entities.Type_client;

import net.olea.santemaghreb.repositories.Type_clientRepository;

@Service
@Transactional
public class Type_clientServiceImpl implements Type_clientService {

private Type_clientRepository type_clientRepository;

public Type_clientServiceImpl(Type_clientRepository type_clientRepository) {
	this.type_clientRepository = type_clientRepository;
}

@Override
public List<Type_client> getType_clients(){
	return type_clientRepository.findAll();
}

@Override
public Optional<Type_client> getType_client(Long id){
	return type_clientRepository.findById(id);
}

@Override
public Type_client addType_client(Type_client type_client){
	type_client.setStatut(Statut.ACTIVE);
	type_client.setDate_creation(Timestamp.from(Instant.now()));
	type_client.setDate_modification(Timestamp.from(Instant.now()));
	return type_clientRepository.save(type_client);
}

@Override
public Type_client updateType_client(Long id, Type_client type_client_new_data){
	Optional<Type_client> otptionalType_client_old  = type_clientRepository.findById(id);
	Type_client type_client_old = otptionalType_client_old.get();

	type_client_new_data.setStatut(type_client_old.getStatut());
	type_client_new_data.setDate_creation(type_client_old.getDate_creation());
	type_client_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return type_clientRepository.save(type_client_new_data);
}

@Override
public void deleteType_client(Long id){
	Optional<Type_client> otptionalType_client = type_clientRepository.findById(id);
	Type_client type_client = otptionalType_client.get();

	type_client.setStatut(type_client.getStatut());
	type_client.setDate_modification(Timestamp.from(Instant.now()));

	type_clientRepository.deleteById(id);
}

}
