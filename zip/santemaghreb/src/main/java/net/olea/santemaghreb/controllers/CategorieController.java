package net.olea.santemaghreb.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.olea.santemaghreb.entities.Categorie;
import net.olea.santemaghreb.services.CategorieService;

@RestController
@RequestMapping(path="/api/")
public class CategorieController {

public CategorieService categorieService;

public CategorieController(CategorieService categorieService) {
	this.categorieService = categorieService;
}

@GetMapping(path="/categories")
public List<Categorie> getCategories(){
	return categorieService.getCategories();
}

@GetMapping(path="/categorie/{id}")
public Optional<Categorie> getCategorie(@PathVariable Long id){
	return categorieService.getCategorie(id);
}

@PostMapping(path="/categorie")
public Categorie addCategorie(@RequestBody Categorie categorie){
	return categorieService.addCategorie(categorie);
}

@PutMapping(path="/categorie/{id}")
public Categorie updateCategorie(@PathVariable Long id, @RequestBody Categorie categorie){
	return categorieService.updateCategorie(id, categorie);
}

@DeleteMapping(path="/categorie/{id}")
public void deleteCategorie(@PathVariable Long id){
	categorieService.deleteCategorie(id);
}

}
