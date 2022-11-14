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

import net.olea.santemaghreb.entities.Compagnie;

import net.olea.santemaghreb.entities.Compagnie;

import net.olea.santemaghreb.repositories.CompagnieRepository;

@Service
@Transactional
public class CompagnieServiceImpl implements CompagnieService {

private CompagnieRepository compagnieRepository;

public CompagnieServiceImpl(CompagnieRepository compagnieRepository) {
	this.compagnieRepository = compagnieRepository;
}

@Override
public List<Compagnie> getCompagnies(){
	return compagnieRepository.findAll();
}

@Override
public Optional<Compagnie> getCompagnie(Long id){
	return compagnieRepository.findById(id);
}

@Override
public Compagnie addCompagnie(Compagnie compagnie){
	compagnie.setStatut(Statut.ACTIVE);
	compagnie.setDate_creation(Timestamp.from(Instant.now()));
	compagnie.setDate_modification(Timestamp.from(Instant.now()));
	return compagnieRepository.save(compagnie);
}

@Override
public Compagnie updateCompagnie(Long id, Compagnie compagnie_new_data){
	Optional<Compagnie> otptionalCompagnie_old  = compagnieRepository.findById(id);
	Compagnie compagnie_old = otptionalCompagnie_old.get();

	compagnie_new_data.setStatut(compagnie_old.getStatut());
	compagnie_new_data.setDate_creation(compagnie_old.getDate_creation());
	compagnie_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return compagnieRepository.save(compagnie_new_data);
}

@Override
public void deleteCompagnie(Long id){
	Optional<Compagnie> otptionalCompagnie = compagnieRepository.findById(id);
	Compagnie compagnie = otptionalCompagnie.get();

	compagnie.setStatut(compagnie.getStatut());
	compagnie.setDate_modification(Timestamp.from(Instant.now()));

	compagnieRepository.deleteById(id);
}

}
