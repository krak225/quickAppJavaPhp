package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Produit;

public interface ProduitService {

	List<Produit> getProduits();
	Optional<Produit> getProduit(Long id);
	Produit addProduit(Produit produit);
	Produit updateProduit(Long id, Produit produit);
	void deleteProduit(Long id);

}
