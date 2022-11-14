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

import net.olea.santemaghreb.entities.Civilite;

import net.olea.santemaghreb.entities.Civilite;

import net.olea.santemaghreb.repositories.CiviliteRepository;

@Service
@Transactional
public class CiviliteServiceImpl implements CiviliteService {

private CiviliteRepository civiliteRepository;

public CiviliteServiceImpl(CiviliteRepository civiliteRepository) {
	this.civiliteRepository = civiliteRepository;
}

@Override
public List<Civilite> getCivilites(){
	return civiliteRepository.findAll();
}

@Override
public Optional<Civilite> getCivilite(Long id){
	return civiliteRepository.findById(id);
}

@Override
public Civilite addCivilite(Civilite civilite){
	civilite.setStatut(Statut.ACTIVE);
	civilite.setDate_creation(Timestamp.from(Instant.now()));
	civilite.setDate_modification(Timestamp.from(Instant.now()));
	return civiliteRepository.save(civilite);
}

@Override
public Civilite updateCivilite(Long id, Civilite civilite_new_data){
	Optional<Civilite> otptionalCivilite_old  = civiliteRepository.findById(id);
	Civilite civilite_old = otptionalCivilite_old.get();

	civilite_new_data.setStatut(civilite_old.getStatut());
	civilite_new_data.setDate_creation(civilite_old.getDate_creation());
	civilite_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return civiliteRepository.save(civilite_new_data);
}

@Override
public void deleteCivilite(Long id){
	Optional<Civilite> otptionalCivilite = civiliteRepository.findById(id);
	Civilite civilite = otptionalCivilite.get();

	civilite.setStatut(civilite.getStatut());
	civilite.setDate_modification(Timestamp.from(Instant.now()));

	civiliteRepository.deleteById(id);
}

}
