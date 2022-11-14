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

import net.olea.santemaghreb.entities.Pays;

import net.olea.santemaghreb.entities.Pays;

import net.olea.santemaghreb.repositories.PaysRepository;

@Service
@Transactional
public class PaysServiceImpl implements PaysService {

private PaysRepository paysRepository;

public PaysServiceImpl(PaysRepository paysRepository) {
	this.paysRepository = paysRepository;
}

@Override
public List<Pays> getPayss(){
	return paysRepository.findAll();
}

@Override
public Optional<Pays> getPays(Long id){
	return paysRepository.findById(id);
}

@Override
public Pays addPays(Pays pays){
	pays.setStatut(Statut.ACTIVE);
	pays.setDate_creation(Timestamp.from(Instant.now()));
	pays.setDate_modification(Timestamp.from(Instant.now()));
	return paysRepository.save(pays);
}

@Override
public Pays updatePays(Long id, Pays pays_new_data){
	Optional<Pays> otptionalPays_old  = paysRepository.findById(id);
	Pays pays_old = otptionalPays_old.get();

	pays_new_data.setStatut(pays_old.getStatut());
	pays_new_data.setDate_creation(pays_old.getDate_creation());
	pays_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return paysRepository.save(pays_new_data);
}

@Override
public void deletePays(Long id){
	Optional<Pays> otptionalPays = paysRepository.findById(id);
	Pays pays = otptionalPays.get();

	pays.setStatut(pays.getStatut());
	pays.setDate_modification(Timestamp.from(Instant.now()));

	paysRepository.deleteById(id);
}

}
