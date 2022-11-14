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

import net.olea.santemaghreb.entities.Type_mouvement;
import net.olea.santemaghreb.services.Type_mouvementService;

@RestController
@RequestMapping(path="/api/")
public class Type_mouvementController {

public Type_mouvementService type_mouvementService;

public Type_mouvementController(Type_mouvementService type_mouvementService) {
	this.type_mouvementService = type_mouvementService;
}

@GetMapping(path="/type_mouvements")
public List<Type_mouvement> getType_mouvements(){
	return type_mouvementService.getType_mouvements();
}

@GetMapping(path="/type_mouvement/{id}")
public Optional<Type_mouvement> getType_mouvement(@PathVariable Long id){
	return type_mouvementService.getType_mouvement(id);
}

@PostMapping(path="/type_mouvement")
public Type_mouvement addType_mouvement(@RequestBody Type_mouvement type_mouvement){
	return type_mouvementService.addType_mouvement(type_mouvement);
}

@PutMapping(path="/type_mouvement/{id}")
public Type_mouvement updateType_mouvement(@PathVariable Long id, @RequestBody Type_mouvement type_mouvement){
	return type_mouvementService.updateType_mouvement(id, type_mouvement);
}

@DeleteMapping(path="/type_mouvement/{id}")
public void deleteType_mouvement(@PathVariable Long id){
	type_mouvementService.deleteType_mouvement(id);
}

}
