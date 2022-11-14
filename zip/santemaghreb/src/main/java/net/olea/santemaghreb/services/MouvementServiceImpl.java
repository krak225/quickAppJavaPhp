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

import net.olea.santemaghreb.entities.Mouvement;

import net.olea.santemaghreb.entities.Mouvement;

import net.olea.santemaghreb.repositories.MouvementRepository;

@Service
@Transactional
public class MouvementServiceImpl implements MouvementService {

private MouvementRepository mouvementRepository;

public MouvementServiceImpl(MouvementRepository mouvementRepository) {
	this.mouvementRepository = mouvementRepository;
}

@Override
public List<Mouvement> getMouvements(){
	return mouvementRepository.findAll();
}

@Override
public Optional<Mouvement> getMouvement(Long id){
	return mouvementRepository.findById(id);
}

@Override
public Mouvement addMouvement(Mouvement mouvement){
	mouvement.setStatut(Statut.ACTIVE);
	mouvement.setDate_creation(Timestamp.from(Instant.now()));
	mouvement.setDate_modification(Timestamp.from(Instant.now()));
	return mouvementRepository.save(mouvement);
}

@Override
public Mouvement updateMouvement(Long id, Mouvement mouvement_new_data){
	Optional<Mouvement> otptionalMouvement_old  = mouvementRepository.findById(id);
	Mouvement mouvement_old = otptionalMouvement_old.get();

	mouvement_new_data.setStatut(mouvement_old.getStatut());
	mouvement_new_data.setDate_creation(mouvement_old.getDate_creation());
	mouvement_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return mouvementRepository.save(mouvement_new_data);
}

@Override
public void deleteMouvement(Long id){
	Optional<Mouvement> otptionalMouvement = mouvementRepository.findById(id);
	Mouvement mouvement = otptionalMouvement.get();

	mouvement.setStatut(mouvement.getStatut());
	mouvement.setDate_modification(Timestamp.from(Instant.now()));

	mouvementRepository.deleteById(id);
}

}
