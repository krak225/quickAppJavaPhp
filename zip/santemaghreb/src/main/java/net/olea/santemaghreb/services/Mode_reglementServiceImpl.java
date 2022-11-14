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

import net.olea.santemaghreb.entities.Mode_reglement;

import net.olea.santemaghreb.entities.Mode_reglement;

import net.olea.santemaghreb.repositories.Mode_reglementRepository;

@Service
@Transactional
public class Mode_reglementServiceImpl implements Mode_reglementService {

private Mode_reglementRepository mode_reglementRepository;

public Mode_reglementServiceImpl(Mode_reglementRepository mode_reglementRepository) {
	this.mode_reglementRepository = mode_reglementRepository;
}

@Override
public List<Mode_reglement> getMode_reglements(){
	return mode_reglementRepository.findAll();
}

@Override
public Optional<Mode_reglement> getMode_reglement(Long id){
	return mode_reglementRepository.findById(id);
}

@Override
public Mode_reglement addMode_reglement(Mode_reglement mode_reglement){
	mode_reglement.setStatut(Statut.ACTIVE);
	mode_reglement.setDate_creation(Timestamp.from(Instant.now()));
	mode_reglement.setDate_modification(Timestamp.from(Instant.now()));
	return mode_reglementRepository.save(mode_reglement);
}

@Override
public Mode_reglement updateMode_reglement(Long id, Mode_reglement mode_reglement_new_data){
	Optional<Mode_reglement> otptionalMode_reglement_old  = mode_reglementRepository.findById(id);
	Mode_reglement mode_reglement_old = otptionalMode_reglement_old.get();

	mode_reglement_new_data.setStatut(mode_reglement_old.getStatut());
	mode_reglement_new_data.setDate_creation(mode_reglement_old.getDate_creation());
	mode_reglement_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return mode_reglementRepository.save(mode_reglement_new_data);
}

@Override
public void deleteMode_reglement(Long id){
	Optional<Mode_reglement> otptionalMode_reglement = mode_reglementRepository.findById(id);
	Mode_reglement mode_reglement = otptionalMode_reglement.get();

	mode_reglement.setStatut(mode_reglement.getStatut());
	mode_reglement.setDate_modification(Timestamp.from(Instant.now()));

	mode_reglementRepository.deleteById(id);
}

}
