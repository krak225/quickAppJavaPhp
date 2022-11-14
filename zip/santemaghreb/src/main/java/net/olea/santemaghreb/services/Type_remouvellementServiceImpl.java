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

import net.olea.santemaghreb.entities.Type_remouvellement;

import net.olea.santemaghreb.entities.Type_remouvellement;

import net.olea.santemaghreb.repositories.Type_remouvellementRepository;

@Service
@Transactional
public class Type_remouvellementServiceImpl implements Type_remouvellementService {

private Type_remouvellementRepository type_remouvellementRepository;

public Type_remouvellementServiceImpl(Type_remouvellementRepository type_remouvellementRepository) {
	this.type_remouvellementRepository = type_remouvellementRepository;
}

@Override
public List<Type_remouvellement> getType_remouvellements(){
	return type_remouvellementRepository.findAll();
}

@Override
public Optional<Type_remouvellement> getType_remouvellement(Long id){
	return type_remouvellementRepository.findById(id);
}

@Override
public Type_remouvellement addType_remouvellement(Type_remouvellement type_remouvellement){
	type_remouvellement.setStatut(Statut.ACTIVE);
	type_remouvellement.setDate_creation(Timestamp.from(Instant.now()));
	type_remouvellement.setDate_modification(Timestamp.from(Instant.now()));
	return type_remouvellementRepository.save(type_remouvellement);
}

@Override
public Type_remouvellement updateType_remouvellement(Long id, Type_remouvellement type_remouvellement_new_data){
	Optional<Type_remouvellement> otptionalType_remouvellement_old  = type_remouvellementRepository.findById(id);
	Type_remouvellement type_remouvellement_old = otptionalType_remouvellement_old.get();

	type_remouvellement_new_data.setStatut(type_remouvellement_old.getStatut());
	type_remouvellement_new_data.setDate_creation(type_remouvellement_old.getDate_creation());
	type_remouvellement_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return type_remouvellementRepository.save(type_remouvellement_new_data);
}

@Override
public void deleteType_remouvellement(Long id){
	Optional<Type_remouvellement> otptionalType_remouvellement = type_remouvellementRepository.findById(id);
	Type_remouvellement type_remouvellement = otptionalType_remouvellement.get();

	type_remouvellement.setStatut(type_remouvellement.getStatut());
	type_remouvellement.setDate_modification(Timestamp.from(Instant.now()));

	type_remouvellementRepository.deleteById(id);
}

}
