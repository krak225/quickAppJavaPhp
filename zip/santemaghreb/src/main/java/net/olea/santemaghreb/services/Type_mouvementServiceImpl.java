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

import net.olea.santemaghreb.entities.Type_mouvement;

import net.olea.santemaghreb.entities.Type_mouvement;

import net.olea.santemaghreb.repositories.Type_mouvementRepository;

@Service
@Transactional
public class Type_mouvementServiceImpl implements Type_mouvementService {

private Type_mouvementRepository type_mouvementRepository;

public Type_mouvementServiceImpl(Type_mouvementRepository type_mouvementRepository) {
	this.type_mouvementRepository = type_mouvementRepository;
}

@Override
public List<Type_mouvement> getType_mouvements(){
	return type_mouvementRepository.findAll();
}

@Override
public Optional<Type_mouvement> getType_mouvement(Long id){
	return type_mouvementRepository.findById(id);
}

@Override
public Type_mouvement addType_mouvement(Type_mouvement type_mouvement){
	type_mouvement.setStatut(Statut.ACTIVE);
	type_mouvement.setDate_creation(Timestamp.from(Instant.now()));
	type_mouvement.setDate_modification(Timestamp.from(Instant.now()));
	return type_mouvementRepository.save(type_mouvement);
}

@Override
public Type_mouvement updateType_mouvement(Long id, Type_mouvement type_mouvement_new_data){
	Optional<Type_mouvement> otptionalType_mouvement_old  = type_mouvementRepository.findById(id);
	Type_mouvement type_mouvement_old = otptionalType_mouvement_old.get();

	type_mouvement_new_data.setStatut(type_mouvement_old.getStatut());
	type_mouvement_new_data.setDate_creation(type_mouvement_old.getDate_creation());
	type_mouvement_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return type_mouvementRepository.save(type_mouvement_new_data);
}

@Override
public void deleteType_mouvement(Long id){
	Optional<Type_mouvement> otptionalType_mouvement = type_mouvementRepository.findById(id);
	Type_mouvement type_mouvement = otptionalType_mouvement.get();

	type_mouvement.setStatut(type_mouvement.getStatut());
	type_mouvement.setDate_modification(Timestamp.from(Instant.now()));

	type_mouvementRepository.deleteById(id);
}

}
