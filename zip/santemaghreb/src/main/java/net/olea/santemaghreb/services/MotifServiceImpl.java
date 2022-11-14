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

import net.olea.santemaghreb.entities.Motif;

import net.olea.santemaghreb.entities.Motif;

import net.olea.santemaghreb.repositories.MotifRepository;

@Service
@Transactional
public class MotifServiceImpl implements MotifService {

private MotifRepository motifRepository;

public MotifServiceImpl(MotifRepository motifRepository) {
	this.motifRepository = motifRepository;
}

@Override
public List<Motif> getMotifs(){
	return motifRepository.findAll();
}

@Override
public Optional<Motif> getMotif(Long id){
	return motifRepository.findById(id);
}

@Override
public Motif addMotif(Motif motif){
	motif.setStatut(Statut.ACTIVE);
	motif.setDate_creation(Timestamp.from(Instant.now()));
	motif.setDate_modification(Timestamp.from(Instant.now()));
	return motifRepository.save(motif);
}

@Override
public Motif updateMotif(Long id, Motif motif_new_data){
	Optional<Motif> otptionalMotif_old  = motifRepository.findById(id);
	Motif motif_old = otptionalMotif_old.get();

	motif_new_data.setStatut(motif_old.getStatut());
	motif_new_data.setDate_creation(motif_old.getDate_creation());
	motif_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return motifRepository.save(motif_new_data);
}

@Override
public void deleteMotif(Long id){
	Optional<Motif> otptionalMotif = motifRepository.findById(id);
	Motif motif = otptionalMotif.get();

	motif.setStatut(motif.getStatut());
	motif.setDate_modification(Timestamp.from(Instant.now()));

	motifRepository.deleteById(id);
}

}
