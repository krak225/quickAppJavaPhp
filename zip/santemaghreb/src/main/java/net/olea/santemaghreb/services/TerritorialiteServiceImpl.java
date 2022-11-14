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

import net.olea.santemaghreb.entities.Territorialite;

import net.olea.santemaghreb.entities.Territorialite;

import net.olea.santemaghreb.repositories.TerritorialiteRepository;

@Service
@Transactional
public class TerritorialiteServiceImpl implements TerritorialiteService {

private TerritorialiteRepository territorialiteRepository;

public TerritorialiteServiceImpl(TerritorialiteRepository territorialiteRepository) {
	this.territorialiteRepository = territorialiteRepository;
}

@Override
public List<Territorialite> getTerritorialites(){
	return territorialiteRepository.findAll();
}

@Override
public Optional<Territorialite> getTerritorialite(Long id){
	return territorialiteRepository.findById(id);
}

@Override
public Territorialite addTerritorialite(Territorialite territorialite){
	territorialite.setStatut(Statut.ACTIVE);
	territorialite.setDate_creation(Timestamp.from(Instant.now()));
	territorialite.setDate_modification(Timestamp.from(Instant.now()));
	return territorialiteRepository.save(territorialite);
}

@Override
public Territorialite updateTerritorialite(Long id, Territorialite territorialite_new_data){
	Optional<Territorialite> otptionalTerritorialite_old  = territorialiteRepository.findById(id);
	Territorialite territorialite_old = otptionalTerritorialite_old.get();

	territorialite_new_data.setStatut(territorialite_old.getStatut());
	territorialite_new_data.setDate_creation(territorialite_old.getDate_creation());
	territorialite_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return territorialiteRepository.save(territorialite_new_data);
}

@Override
public void deleteTerritorialite(Long id){
	Optional<Territorialite> otptionalTerritorialite = territorialiteRepository.findById(id);
	Territorialite territorialite = otptionalTerritorialite.get();

	territorialite.setStatut(territorialite.getStatut());
	territorialite.setDate_modification(Timestamp.from(Instant.now()));

	territorialiteRepository.deleteById(id);
}

}
