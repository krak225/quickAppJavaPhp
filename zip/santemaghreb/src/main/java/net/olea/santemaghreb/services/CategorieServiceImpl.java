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

import net.olea.santemaghreb.entities.Categorie;

import net.olea.santemaghreb.entities.Categorie;

import net.olea.santemaghreb.repositories.CategorieRepository;

@Service
@Transactional
public class CategorieServiceImpl implements CategorieService {

private CategorieRepository categorieRepository;

public CategorieServiceImpl(CategorieRepository categorieRepository) {
	this.categorieRepository = categorieRepository;
}

@Override
public List<Categorie> getCategories(){
	return categorieRepository.findAll();
}

@Override
public Optional<Categorie> getCategorie(Long id){
	return categorieRepository.findById(id);
}

@Override
public Categorie addCategorie(Categorie categorie){
	categorie.setStatut(Statut.ACTIVE);
	categorie.setDate_creation(Timestamp.from(Instant.now()));
	categorie.setDate_modification(Timestamp.from(Instant.now()));
	return categorieRepository.save(categorie);
}

@Override
public Categorie updateCategorie(Long id, Categorie categorie_new_data){
	Optional<Categorie> otptionalCategorie_old  = categorieRepository.findById(id);
	Categorie categorie_old = otptionalCategorie_old.get();

	categorie_new_data.setStatut(categorie_old.getStatut());
	categorie_new_data.setDate_creation(categorie_old.getDate_creation());
	categorie_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return categorieRepository.save(categorie_new_data);
}

@Override
public void deleteCategorie(Long id){
	Optional<Categorie> otptionalCategorie = categorieRepository.findById(id);
	Categorie categorie = otptionalCategorie.get();

	categorie.setStatut(categorie.getStatut());
	categorie.setDate_modification(Timestamp.from(Instant.now()));

	categorieRepository.deleteById(id);
}

}
