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

import net.olea.santemaghreb.entities.Type_remouvellement;
import net.olea.santemaghreb.services.Type_remouvellementService;

@RestController
@RequestMapping(path="/api/")
public class Type_remouvellementController {

public Type_remouvellementService type_remouvellementService;

public Type_remouvellementController(Type_remouvellementService type_remouvellementService) {
	this.type_remouvellementService = type_remouvellementService;
}

@GetMapping(path="/type_remouvellements")
public List<Type_remouvellement> getType_remouvellements(){
	return type_remouvellementService.getType_remouvellements();
}

@GetMapping(path="/type_remouvellement/{id}")
public Optional<Type_remouvellement> getType_remouvellement(@PathVariable Long id){
	return type_remouvellementService.getType_remouvellement(id);
}

@PostMapping(path="/type_remouvellement")
public Type_remouvellement addType_remouvellement(@RequestBody Type_remouvellement type_remouvellement){
	return type_remouvellementService.addType_remouvellement(type_remouvellement);
}

@PutMapping(path="/type_remouvellement/{id}")
public Type_remouvellement updateType_remouvellement(@PathVariable Long id, @RequestBody Type_remouvellement type_remouvellement){
	return type_remouvellementService.updateType_remouvellement(id, type_remouvellement);
}

@DeleteMapping(path="/type_remouvellement/{id}")
public void deleteType_remouvellement(@PathVariable Long id){
	type_remouvellementService.deleteType_remouvellement(id);
}

}
