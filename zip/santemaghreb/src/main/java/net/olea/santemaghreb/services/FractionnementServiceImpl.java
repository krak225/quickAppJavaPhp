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

import net.olea.santemaghreb.entities.Fractionnement;

import net.olea.santemaghreb.entities.Fractionnement;

import net.olea.santemaghreb.repositories.FractionnementRepository;

@Service
@Transactional
public class FractionnementServiceImpl implements FractionnementService {

private FractionnementRepository fractionnementRepository;

public FractionnementServiceImpl(FractionnementRepository fractionnementRepository) {
	this.fractionnementRepository = fractionnementRepository;
}

@Override
public List<Fractionnement> getFractionnements(){
	return fractionnementRepository.findAll();
}

@Override
public Optional<Fractionnement> getFractionnement(Long id){
	return fractionnementRepository.findById(id);
}

@Override
public Fractionnement addFractionnement(Fractionnement fractionnement){
	fractionnement.setStatut(Statut.ACTIVE);
	fractionnement.setDate_creation(Timestamp.from(Instant.now()));
	fractionnement.setDate_modification(Timestamp.from(Instant.now()));
	return fractionnementRepository.save(fractionnement);
}

@Override
public Fractionnement updateFractionnement(Long id, Fractionnement fractionnement_new_data){
	Optional<Fractionnement> otptionalFractionnement_old  = fractionnementRepository.findById(id);
	Fractionnement fractionnement_old = otptionalFractionnement_old.get();

	fractionnement_new_data.setStatut(fractionnement_old.getStatut());
	fractionnement_new_data.setDate_creation(fractionnement_old.getDate_creation());
	fractionnement_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return fractionnementRepository.save(fractionnement_new_data);
}

@Override
public void deleteFractionnement(Long id){
	Optional<Fractionnement> otptionalFractionnement = fractionnementRepository.findById(id);
	Fractionnement fractionnement = otptionalFractionnement.get();

	fractionnement.setStatut(fractionnement.getStatut());
	fractionnement.setDate_modification(Timestamp.from(Instant.now()));

	fractionnementRepository.deleteById(id);
}

}
