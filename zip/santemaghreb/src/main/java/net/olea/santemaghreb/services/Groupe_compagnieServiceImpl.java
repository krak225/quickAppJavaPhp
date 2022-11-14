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

import net.olea.santemaghreb.entities.Groupe_compagnie;

import net.olea.santemaghreb.entities.Groupe_compagnie;

import net.olea.santemaghreb.repositories.Groupe_compagnieRepository;

@Service
@Transactional
public class Groupe_compagnieServiceImpl implements Groupe_compagnieService {

private Groupe_compagnieRepository groupe_compagnieRepository;

public Groupe_compagnieServiceImpl(Groupe_compagnieRepository groupe_compagnieRepository) {
	this.groupe_compagnieRepository = groupe_compagnieRepository;
}

@Override
public List<Groupe_compagnie> getGroupe_compagnies(){
	return groupe_compagnieRepository.findAll();
}

@Override
public Optional<Groupe_compagnie> getGroupe_compagnie(Long id){
	return groupe_compagnieRepository.findById(id);
}

@Override
public Groupe_compagnie addGroupe_compagnie(Groupe_compagnie groupe_compagnie){
	groupe_compagnie.setStatut(Statut.ACTIVE);
	groupe_compagnie.setDate_creation(Timestamp.from(Instant.now()));
	groupe_compagnie.setDate_modification(Timestamp.from(Instant.now()));
	return groupe_compagnieRepository.save(groupe_compagnie);
}

@Override
public Groupe_compagnie updateGroupe_compagnie(Long id, Groupe_compagnie groupe_compagnie_new_data){
	Optional<Groupe_compagnie> otptionalGroupe_compagnie_old  = groupe_compagnieRepository.findById(id);
	Groupe_compagnie groupe_compagnie_old = otptionalGroupe_compagnie_old.get();

	groupe_compagnie_new_data.setStatut(groupe_compagnie_old.getStatut());
	groupe_compagnie_new_data.setDate_creation(groupe_compagnie_old.getDate_creation());
	groupe_compagnie_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return groupe_compagnieRepository.save(groupe_compagnie_new_data);
}

@Override
public void deleteGroupe_compagnie(Long id){
	Optional<Groupe_compagnie> otptionalGroupe_compagnie = groupe_compagnieRepository.findById(id);
	Groupe_compagnie groupe_compagnie = otptionalGroupe_compagnie.get();

	groupe_compagnie.setStatut(groupe_compagnie.getStatut());
	groupe_compagnie.setDate_modification(Timestamp.from(Instant.now()));

	groupe_compagnieRepository.deleteById(id);
}

}
