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

import net.olea.santemaghreb.entities.Type_connexite;

import net.olea.santemaghreb.entities.Type_connexite;

import net.olea.santemaghreb.repositories.Type_connexiteRepository;

@Service
@Transactional
public class Type_connexiteServiceImpl implements Type_connexiteService {

private Type_connexiteRepository type_connexiteRepository;

public Type_connexiteServiceImpl(Type_connexiteRepository type_connexiteRepository) {
	this.type_connexiteRepository = type_connexiteRepository;
}

@Override
public List<Type_connexite> getType_connexites(){
	return type_connexiteRepository.findAll();
}

@Override
public Optional<Type_connexite> getType_connexite(Long id){
	return type_connexiteRepository.findById(id);
}

@Override
public Type_connexite addType_connexite(Type_connexite type_connexite){
	type_connexite.setStatut(Statut.ACTIVE);
	type_connexite.setDate_creation(Timestamp.from(Instant.now()));
	type_connexite.setDate_modification(Timestamp.from(Instant.now()));
	return type_connexiteRepository.save(type_connexite);
}

@Override
public Type_connexite updateType_connexite(Long id, Type_connexite type_connexite_new_data){
	Optional<Type_connexite> otptionalType_connexite_old  = type_connexiteRepository.findById(id);
	Type_connexite type_connexite_old = otptionalType_connexite_old.get();

	type_connexite_new_data.setStatut(type_connexite_old.getStatut());
	type_connexite_new_data.setDate_creation(type_connexite_old.getDate_creation());
	type_connexite_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return type_connexiteRepository.save(type_connexite_new_data);
}

@Override
public void deleteType_connexite(Long id){
	Optional<Type_connexite> otptionalType_connexite = type_connexiteRepository.findById(id);
	Type_connexite type_connexite = otptionalType_connexite.get();

	type_connexite.setStatut(type_connexite.getStatut());
	type_connexite.setDate_modification(Timestamp.from(Instant.now()));

	type_connexiteRepository.deleteById(id);
}

}
