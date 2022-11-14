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

import net.olea.santemaghreb.entities.Type_regulation;
import net.olea.santemaghreb.services.Type_regulationService;

@RestController
@RequestMapping(path="/api/")
public class Type_regulationController {

public Type_regulationService type_regulationService;

public Type_regulationController(Type_regulationService type_regulationService) {
	this.type_regulationService = type_regulationService;
}

@GetMapping(path="/type_regulations")
public List<Type_regulation> getType_regulations(){
	return type_regulationService.getType_regulations();
}

@GetMapping(path="/type_regulation/{id}")
public Optional<Type_regulation> getType_regulation(@PathVariable Long id){
	return type_regulationService.getType_regulation(id);
}

@PostMapping(path="/type_regulation")
public Type_regulation addType_regulation(@RequestBody Type_regulation type_regulation){
	return type_regulationService.addType_regulation(type_regulation);
}

@PutMapping(path="/type_regulation/{id}")
public Type_regulation updateType_regulation(@PathVariable Long id, @RequestBody Type_regulation type_regulation){
	return type_regulationService.updateType_regulation(id, type_regulation);
}

@DeleteMapping(path="/type_regulation/{id}")
public void deleteType_regulation(@PathVariable Long id){
	type_regulationService.deleteType_regulation(id);
}

}
