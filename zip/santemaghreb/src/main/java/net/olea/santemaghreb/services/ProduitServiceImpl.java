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

import net.olea.santemaghreb.entities.Produit;

import net.olea.santemaghreb.entities.Produit;

import net.olea.santemaghreb.repositories.ProduitRepository;

@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

private ProduitRepository produitRepository;

public ProduitServiceImpl(ProduitRepository produitRepository) {
	this.produitRepository = produitRepository;
}

@Override
public List<Produit> getProduits(){
	return produitRepository.findAll();
}

@Override
public Optional<Produit> getProduit(Long id){
	return produitRepository.findById(id);
}

@Override
public Produit addProduit(Produit produit){
	produit.setStatut(Statut.ACTIVE);
	produit.setDate_creation(Timestamp.from(Instant.now()));
	produit.setDate_modification(Timestamp.from(Instant.now()));
	return produitRepository.save(produit);
}

@Override
public Produit updateProduit(Long id, Produit produit_new_data){
	Optional<Produit> otptionalProduit_old  = produitRepository.findById(id);
	Produit produit_old = otptionalProduit_old.get();

	produit_new_data.setStatut(produit_old.getStatut());
	produit_new_data.setDate_creation(produit_old.getDate_creation());
	produit_new_data.setDate_modification(Timestamp.from(Instant.now()));

	return produitRepository.save(produit_new_data);
}

@Override
public void deleteProduit(Long id){
	Optional<Produit> otptionalProduit = produitRepository.findById(id);
	Produit produit = otptionalProduit.get();

	produit.setStatut(produit.getStatut());
	produit.setDate_modification(Timestamp.from(Instant.now()));

	produitRepository.deleteById(id);
}

}
