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

import net.olea.santemaghreb.entities.Type_personne;

import net.olea.santemaghreb.entities.Type_personne;

import net.olea.santemaghreb.repositories.Type_personneRepository;

@Service
@Transactional
public class Type_personneServiceImpl implements Type_personneService {

private Type_personneRepository type_personneRepository;

public Type_personneServiceImpl(Type_personneRepository type_personneRepository) {
	this.type_personneRepository = type_personneRepository;
}

@Override
public List<Type_personne> getType_personnes(){
	return type_personneRepository.findAll();
}

@Override
public Optional<Type_personne> getType_personne(Long id){
	return type_personneRepository.findById(id);
}

@Override
public Type_personne addType_personne(Type_personne type_personne){
	type_personne.setStatut(Statut.ACTIVE);
	type_personne.setDate_creation(Timestamp.from(Instant.now()));
	type_personne.setDate_modification(Timestamp.from(Instant.now()));
	return type_personneRepository.save(type_personne);
}

@Override
public Type_personne updateType_personne(Long id, Type_personne type_personne_new_data){
	Optional<Type_personne> otptionalType_personne_old  = type_personneRepository.findById(id);
	Type_personne type_personne_old = otptionalType_personne_old.get();

	type_personne_new_data.setStatut(type_personne_old.getStatut());
	type_personne_new_data.setDate_creation(type_personne_old.getDate_creation());
	type_personne_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return type_personneRepository.save(type_personne_new_data);
}

@Override
public void deleteType_personne(Long id){
	Optional<Type_personne> otptionalType_personne = type_personneRepository.findById(id);
	Type_personne type_personne = otptionalType_personne.get();

	type_personne.setStatut(type_personne.getStatut());
	type_personne.setDate_modification(Timestamp.from(Instant.now()));

	type_personneRepository.deleteById(id);
}

}
