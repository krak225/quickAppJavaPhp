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

import net.olea.santemaghreb.entities.Bureau;

import net.olea.santemaghreb.entities.Bureau;

import net.olea.santemaghreb.repositories.BureauRepository;

@Service
@Transactional
public class BureauServiceImpl implements BureauService {

private BureauRepository bureauRepository;

public BureauServiceImpl(BureauRepository bureauRepository) {
	this.bureauRepository = bureauRepository;
}

@Override
public List<Bureau> getBureaus(){
	return bureauRepository.findAll();
}

@Override
public Optional<Bureau> getBureau(Long id){
	return bureauRepository.findById(id);
}

@Override
public Bureau addBureau(Bureau bureau){
	bureau.setStatut(Statut.ACTIVE);
	bureau.setDate_creation(Timestamp.from(Instant.now()));
	bureau.setDate_modification(Timestamp.from(Instant.now()));
	return bureauRepository.save(bureau);
}

@Override
public Bureau updateBureau(Long id, Bureau bureau_new_data){
	Optional<Bureau> otptionalBureau_old  = bureauRepository.findById(id);
	Bureau bureau_old = otptionalBureau_old.get();

	bureau_new_data.setStatut(bureau_old.getStatut());
	bureau_new_data.setDate_creation(bureau_old.getDate_creation());
	bureau_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return bureauRepository.save(bureau_new_data);
}

@Override
public void deleteBureau(Long id){
	Optional<Bureau> otptionalBureau = bureauRepository.findById(id);
	Bureau bureau = otptionalBureau.get();

	bureau.setStatut(bureau.getStatut());
	bureau.setDate_modification(Timestamp.from(Instant.now()));

	bureauRepository.deleteById(id);
}

}
