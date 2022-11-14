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

import net.olea.santemaghreb.entities.Qualite_beneficiaire;

import net.olea.santemaghreb.entities.Qualite_beneficiaire;

import net.olea.santemaghreb.repositories.Qualite_beneficiaireRepository;

@Service
@Transactional
public class Qualite_beneficiaireServiceImpl implements Qualite_beneficiaireService {

private Qualite_beneficiaireRepository qualite_beneficiaireRepository;

public Qualite_beneficiaireServiceImpl(Qualite_beneficiaireRepository qualite_beneficiaireRepository) {
	this.qualite_beneficiaireRepository = qualite_beneficiaireRepository;
}

@Override
public List<Qualite_beneficiaire> getQualite_beneficiaires(){
	return qualite_beneficiaireRepository.findAll();
}

@Override
public Optional<Qualite_beneficiaire> getQualite_beneficiaire(Long id){
	return qualite_beneficiaireRepository.findById(id);
}

@Override
public Qualite_beneficiaire addQualite_beneficiaire(Qualite_beneficiaire qualite_beneficiaire){
	qualite_beneficiaire.setStatut(Statut.ACTIVE);
	qualite_beneficiaire.setDate_creation(Timestamp.from(Instant.now()));
	qualite_beneficiaire.setDate_modification(Timestamp.from(Instant.now()));
	return qualite_beneficiaireRepository.save(qualite_beneficiaire);
}

@Override
public Qualite_beneficiaire updateQualite_beneficiaire(Long id, Qualite_beneficiaire qualite_beneficiaire_new_data){
	Optional<Qualite_beneficiaire> otptionalQualite_beneficiaire_old  = qualite_beneficiaireRepository.findById(id);
	Qualite_beneficiaire qualite_beneficiaire_old = otptionalQualite_beneficiaire_old.get();

	qualite_beneficiaire_new_data.setStatut(qualite_beneficiaire_old.getStatut());
	qualite_beneficiaire_new_data.setDate_creation(qualite_beneficiaire_old.getDate_creation());
	qualite_beneficiaire_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return qualite_beneficiaireRepository.save(qualite_beneficiaire_new_data);
}

@Override
public void deleteQualite_beneficiaire(Long id){
	Optional<Qualite_beneficiaire> otptionalQualite_beneficiaire = qualite_beneficiaireRepository.findById(id);
	Qualite_beneficiaire qualite_beneficiaire = otptionalQualite_beneficiaire.get();

	qualite_beneficiaire.setStatut(qualite_beneficiaire.getStatut());
	qualite_beneficiaire.setDate_modification(Timestamp.from(Instant.now()));

	qualite_beneficiaireRepository.deleteById(id);
}

}
