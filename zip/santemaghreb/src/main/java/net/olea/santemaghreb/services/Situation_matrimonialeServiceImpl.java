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

import net.olea.santemaghreb.entities.Situation_matrimoniale;

import net.olea.santemaghreb.entities.Situation_matrimoniale;

import net.olea.santemaghreb.repositories.Situation_matrimonialeRepository;

@Service
@Transactional
public class Situation_matrimonialeServiceImpl implements Situation_matrimonialeService {

private Situation_matrimonialeRepository situation_matrimonialeRepository;

public Situation_matrimonialeServiceImpl(Situation_matrimonialeRepository situation_matrimonialeRepository) {
	this.situation_matrimonialeRepository = situation_matrimonialeRepository;
}

@Override
public List<Situation_matrimoniale> getSituation_matrimoniales(){
	return situation_matrimonialeRepository.findAll();
}

@Override
public Optional<Situation_matrimoniale> getSituation_matrimoniale(Long id){
	return situation_matrimonialeRepository.findById(id);
}

@Override
public Situation_matrimoniale addSituation_matrimoniale(Situation_matrimoniale situation_matrimoniale){
	situation_matrimoniale.setStatut(Statut.ACTIVE);
	situation_matrimoniale.setDate_creation(Timestamp.from(Instant.now()));
	situation_matrimoniale.setDate_modification(Timestamp.from(Instant.now()));
	return situation_matrimonialeRepository.save(situation_matrimoniale);
}

@Override
public Situation_matrimoniale updateSituation_matrimoniale(Long id, Situation_matrimoniale situation_matrimoniale_new_data){
	Optional<Situation_matrimoniale> otptionalSituation_matrimoniale_old  = situation_matrimonialeRepository.findById(id);
	Situation_matrimoniale situation_matrimoniale_old = otptionalSituation_matrimoniale_old.get();

	situation_matrimoniale_new_data.setStatut(situation_matrimoniale_old.getStatut());
	situation_matrimoniale_new_data.setDate_creation(situation_matrimoniale_old.getDate_creation());
	situation_matrimoniale_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return situation_matrimonialeRepository.save(situation_matrimoniale_new_data);
}

@Override
public void deleteSituation_matrimoniale(Long id){
	Optional<Situation_matrimoniale> otptionalSituation_matrimoniale = situation_matrimonialeRepository.findById(id);
	Situation_matrimoniale situation_matrimoniale = otptionalSituation_matrimoniale.get();

	situation_matrimoniale.setStatut(situation_matrimoniale.getStatut());
	situation_matrimoniale.setDate_modification(Timestamp.from(Instant.now()));

	situation_matrimonialeRepository.deleteById(id);
}

}
