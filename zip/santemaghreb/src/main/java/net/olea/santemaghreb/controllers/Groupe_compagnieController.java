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

import net.olea.santemaghreb.entities.Groupe_compagnie;
import net.olea.santemaghreb.services.Groupe_compagnieService;

@RestController
@RequestMapping(path="/api/")
public class Groupe_compagnieController {

public Groupe_compagnieService groupe_compagnieService;

public Groupe_compagnieController(Groupe_compagnieService groupe_compagnieService) {
	this.groupe_compagnieService = groupe_compagnieService;
}

@GetMapping(path="/groupe_compagnies")
public List<Groupe_compagnie> getGroupe_compagnies(){
	return groupe_compagnieService.getGroupe_compagnies();
}

@GetMapping(path="/groupe_compagnie/{id}")
public Optional<Groupe_compagnie> getGroupe_compagnie(@PathVariable Long id){
	return groupe_compagnieService.getGroupe_compagnie(id);
}

@PostMapping(path="/groupe_compagnie")
public Groupe_compagnie addGroupe_compagnie(@RequestBody Groupe_compagnie groupe_compagnie){
	return groupe_compagnieService.addGroupe_compagnie(groupe_compagnie);
}

@PutMapping(path="/groupe_compagnie/{id}")
public Groupe_compagnie updateGroupe_compagnie(@PathVariable Long id, @RequestBody Groupe_compagnie groupe_compagnie){
	return groupe_compagnieService.updateGroupe_compagnie(id, groupe_compagnie);
}

@DeleteMapping(path="/groupe_compagnie/{id}")
public void deleteGroupe_compagnie(@PathVariable Long id){
	groupe_compagnieService.deleteGroupe_compagnie(id);
}

}
