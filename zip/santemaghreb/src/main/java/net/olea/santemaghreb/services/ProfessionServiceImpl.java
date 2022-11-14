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

import net.olea.santemaghreb.entities.Profession;

import net.olea.santemaghreb.entities.Profession;

import net.olea.santemaghreb.repositories.ProfessionRepository;

@Service
@Transactional
public class ProfessionServiceImpl implements ProfessionService {

private ProfessionRepository professionRepository;

public ProfessionServiceImpl(ProfessionRepository professionRepository) {
	this.professionRepository = professionRepository;
}

@Override
public List<Profession> getProfessions(){
	return professionRepository.findAll();
}

@Override
public Optional<Profession> getProfession(Long id){
	return professionRepository.findById(id);
}

@Override
public Profession addProfession(Profession profession){
	profession.setStatut(Statut.ACTIVE);
	profession.setDate_creation(Timestamp.from(Instant.now()));
	profession.setDate_modification(Timestamp.from(Instant.now()));
	return professionRepository.save(profession);
}

@Override
public Profession updateProfession(Long id, Profession profession_new_data){
	Optional<Profession> otptionalProfession_old  = professionRepository.findById(id);
	Profession profession_old = otptionalProfession_old.get();

	profession_new_data.setStatut(profession_old.getStatut());
	profession_new_data.setDate_creation(profession_old.getDate_creation());
	profession_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return professionRepository.save(profession_new_data);
}

@Override
public void deleteProfession(Long id){
	Optional<Profession> otptionalProfession = professionRepository.findById(id);
	Profession profession = otptionalProfession.get();

	profession.setStatut(profession.getStatut());
	profession.setDate_modification(Timestamp.from(Instant.now()));

	professionRepository.deleteById(id);
}

}
