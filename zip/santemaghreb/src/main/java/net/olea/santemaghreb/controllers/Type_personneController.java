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

import net.olea.santemaghreb.entities.Type_personne;
import net.olea.santemaghreb.services.Type_personneService;

@RestController
@RequestMapping(path="/api/")
public class Type_personneController {

public Type_personneService type_personneService;

public Type_personneController(Type_personneService type_personneService) {
	this.type_personneService = type_personneService;
}

@GetMapping(path="/type_personnes")
public List<Type_personne> getType_personnes(){
	return type_personneService.getType_personnes();
}

@GetMapping(path="/type_personne/{id}")
public Optional<Type_personne> getType_personne(@PathVariable Long id){
	return type_personneService.getType_personne(id);
}

@PostMapping(path="/type_personne")
public Type_personne addType_personne(@RequestBody Type_personne type_personne){
	return type_personneService.addType_personne(type_personne);
}

@PutMapping(path="/type_personne/{id}")
public Type_personne updateType_personne(@PathVariable Long id, @RequestBody Type_personne type_personne){
	return type_personneService.updateType_personne(id, type_personne);
}

@DeleteMapping(path="/type_personne/{id}")
public void deleteType_personne(@PathVariable Long id){
	type_personneService.deleteType_personne(id);
}

}
