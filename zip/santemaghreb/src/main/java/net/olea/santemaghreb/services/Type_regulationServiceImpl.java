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

import net.olea.santemaghreb.entities.Type_regulation;

import net.olea.santemaghreb.entities.Type_regulation;

import net.olea.santemaghreb.repositories.Type_regulationRepository;

@Service
@Transactional
public class Type_regulationServiceImpl implements Type_regulationService {

private Type_regulationRepository type_regulationRepository;

public Type_regulationServiceImpl(Type_regulationRepository type_regulationRepository) {
	this.type_regulationRepository = type_regulationRepository;
}

@Override
public List<Type_regulation> getType_regulations(){
	return type_regulationRepository.findAll();
}

@Override
public Optional<Type_regulation> getType_regulation(Long id){
	return type_regulationRepository.findById(id);
}

@Override
public Type_regulation addType_regulation(Type_regulation type_regulation){
	type_regulation.setStatut(Statut.ACTIVE);
	type_regulation.setDate_creation(Timestamp.from(Instant.now()));
	type_regulation.setDate_modification(Timestamp.from(Instant.now()));
	return type_regulationRepository.save(type_regulation);
}

@Override
public Type_regulation updateType_regulation(Long id, Type_regulation type_regulation_new_data){
	Optional<Type_regulation> otptionalType_regulation_old  = type_regulationRepository.findById(id);
	Type_regulation type_regulation_old = otptionalType_regulation_old.get();

	type_regulation_new_data.setStatut(type_regulation_old.getStatut());
	type_regulation_new_data.setDate_creation(type_regulation_old.getDate_creation());
	type_regulation_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return type_regulationRepository.save(type_regulation_new_data);
}

@Override
public void deleteType_regulation(Long id){
	Optional<Type_regulation> otptionalType_regulation = type_regulationRepository.findById(id);
	Type_regulation type_regulation = otptionalType_regulation.get();

	type_regulation.setStatut(type_regulation.getStatut());
	type_regulation.setDate_modification(Timestamp.from(Instant.now()));

	type_regulationRepository.deleteById(id);
}

}
