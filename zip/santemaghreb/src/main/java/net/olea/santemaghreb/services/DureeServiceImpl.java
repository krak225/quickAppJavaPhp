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

import net.olea.santemaghreb.entities.Duree;

import net.olea.santemaghreb.entities.Duree;

import net.olea.santemaghreb.repositories.DureeRepository;

@Service
@Transactional
public class DureeServiceImpl implements DureeService {

private DureeRepository dureeRepository;

public DureeServiceImpl(DureeRepository dureeRepository) {
	this.dureeRepository = dureeRepository;
}

@Override
public List<Duree> getDurees(){
	return dureeRepository.findAll();
}

@Override
public Optional<Duree> getDuree(Long id){
	return dureeRepository.findById(id);
}

@Override
public Duree addDuree(Duree duree){
	duree.setStatut(Statut.ACTIVE);
	duree.setDate_creation(Timestamp.from(Instant.now()));
	duree.setDate_modification(Timestamp.from(Instant.now()));
	return dureeRepository.save(duree);
}

@Override
public Duree updateDuree(Long id, Duree duree_new_data){
	Optional<Duree> otptionalDuree_old  = dureeRepository.findById(id);
	Duree duree_old = otptionalDuree_old.get();

	duree_new_data.setStatut(duree_old.getStatut());
	duree_new_data.setDate_creation(duree_old.getDate_creation());
	duree_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return dureeRepository.save(duree_new_data);
}

@Override
public void deleteDuree(Long id){
	Optional<Duree> otptionalDuree = dureeRepository.findById(id);
	Duree duree = otptionalDuree.get();

	duree.setStatut(duree.getStatut());
	duree.setDate_modification(Timestamp.from(Instant.now()));

	dureeRepository.deleteById(id);
}

}
