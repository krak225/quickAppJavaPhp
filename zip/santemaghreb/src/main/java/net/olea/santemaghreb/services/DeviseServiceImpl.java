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

import net.olea.santemaghreb.entities.Devise;

import net.olea.santemaghreb.entities.Devise;

import net.olea.santemaghreb.repositories.DeviseRepository;

@Service
@Transactional
public class DeviseServiceImpl implements DeviseService {

private DeviseRepository deviseRepository;

public DeviseServiceImpl(DeviseRepository deviseRepository) {
	this.deviseRepository = deviseRepository;
}

@Override
public List<Devise> getDevises(){
	return deviseRepository.findAll();
}

@Override
public Optional<Devise> getDevise(Long id){
	return deviseRepository.findById(id);
}

@Override
public Devise addDevise(Devise devise){
	devise.setStatut(Statut.ACTIVE);
	devise.setDate_creation(Timestamp.from(Instant.now()));
	devise.setDate_modification(Timestamp.from(Instant.now()));
	return deviseRepository.save(devise);
}

@Override
public Devise updateDevise(Long id, Devise devise_new_data){
	Optional<Devise> otptionalDevise_old  = deviseRepository.findById(id);
	Devise devise_old = otptionalDevise_old.get();

	devise_new_data.setStatut(devise_old.getStatut());
	devise_new_data.setDate_creation(devise_old.getDate_creation());
	devise_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return deviseRepository.save(devise_new_data);
}

@Override
public void deleteDevise(Long id){
	Optional<Devise> otptionalDevise = deviseRepository.findById(id);
	Devise devise = otptionalDevise.get();

	devise.setStatut(devise.getStatut());
	devise.setDate_modification(Timestamp.from(Instant.now()));

	deviseRepository.deleteById(id);
}

}
