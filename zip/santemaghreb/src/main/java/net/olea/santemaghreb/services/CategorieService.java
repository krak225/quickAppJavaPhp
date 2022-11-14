package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Categorie;

public interface CategorieService {

	List<Categorie> getCategories();
	Optional<Categorie> getCategorie(Long id);
	Categorie addCategorie(Categorie categorie);
	Categorie updateCategorie(Long id, Categorie categorie);
	void deleteCategorie(Long id);

}
