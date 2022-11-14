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

import net.olea.santemaghreb.entities.Type_connexite;
import net.olea.santemaghreb.services.Type_connexiteService;

@RestController
@RequestMapping(path="/api/")
public class Type_connexiteController {

public Type_connexiteService type_connexiteService;

public Type_connexiteController(Type_connexiteService type_connexiteService) {
	this.type_connexiteService = type_connexiteService;
}

@GetMapping(path="/type_connexites")
public List<Type_connexite> getType_connexites(){
	return type_connexiteService.getType_connexites();
}

@GetMapping(path="/type_connexite/{id}")
public Optional<Type_connexite> getType_connexite(@PathVariable Long id){
	return type_connexiteService.getType_connexite(id);
}

@PostMapping(path="/type_connexite")
public Type_connexite addType_connexite(@RequestBody Type_connexite type_connexite){
	return type_connexiteService.addType_connexite(type_connexite);
}

@PutMapping(path="/type_connexite/{id}")
public Type_connexite updateType_connexite(@PathVariable Long id, @RequestBody Type_connexite type_connexite){
	return type_connexiteService.updateType_connexite(id, type_connexite);
}

@DeleteMapping(path="/type_connexite/{id}")
public void deleteType_connexite(@PathVariable Long id){
	type_connexiteService.deleteType_connexite(id);
}

}
