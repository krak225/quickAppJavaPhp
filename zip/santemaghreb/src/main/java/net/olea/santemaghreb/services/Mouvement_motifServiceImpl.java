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

import net.olea.santemaghreb.entities.Mouvement_motif;

import net.olea.santemaghreb.entities.Mouvement_motif;

import net.olea.santemaghreb.repositories.Mouvement_motifRepository;

@Service
@Transactional
public class Mouvement_motifServiceImpl implements Mouvement_motifService {

private Mouvement_motifRepository mouvement_motifRepository;

public Mouvement_motifServiceImpl(Mouvement_motifRepository mouvement_motifRepository) {
	this.mouvement_motifRepository = mouvement_motifRepository;
}

@Override
public List<Mouvement_motif> getMouvement_motifs(){
	return mouvement_motifRepository.findAll();
}

@Override
public Optional<Mouvement_motif> getMouvement_motif(Long id){
	return mouvement_motifRepository.findById(id);
}

@Override
public Mouvement_motif addMouvement_motif(Mouvement_motif mouvement_motif){
	mouvement_motif.setStatut(Statut.ACTIVE);
	mouvement_motif.setDate_creation(Timestamp.from(Instant.now()));
	mouvement_motif.setDate_modification(Timestamp.from(Instant.now()));
	return mouvement_motifRepository.save(mouvement_motif);
}

@Override
public Mouvement_motif updateMouvement_motif(Long id, Mouvement_motif mouvement_motif_new_data){
	Optional<Mouvement_motif> otptionalMouvement_motif_old  = mouvement_motifRepository.findById(id);
	Mouvement_motif mouvement_motif_old = otptionalMouvement_motif_old.get();

	mouvement_motif_new_data.setStatut(mouvement_motif_old.getStatut());
	mouvement_motif_new_data.setDate_creation(mouvement_motif_old.getDate_creation());
	mouvement_motif_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return mouvement_motifRepository.save(mouvement_motif_new_data);
}

@Override
public void deleteMouvement_motif(Long id){
	Optional<Mouvement_motif> otptionalMouvement_motif = mouvement_motifRepository.findById(id);
	Mouvement_motif mouvement_motif = otptionalMouvement_motif.get();

	mouvement_motif.setStatut(mouvement_motif.getStatut());
	mouvement_motif.setDate_modification(Timestamp.from(Instant.now()));

	mouvement_motifRepository.deleteById(id);
}

}
