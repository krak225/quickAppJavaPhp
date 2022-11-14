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

import net.olea.santemaghreb.entities.Type_client;
import net.olea.santemaghreb.services.Type_clientService;

@RestController
@RequestMapping(path="/api/")
public class Type_clientController {

public Type_clientService type_clientService;

public Type_clientController(Type_clientService type_clientService) {
	this.type_clientService = type_clientService;
}

@GetMapping(path="/type_clients")
public List<Type_client> getType_clients(){
	return type_clientService.getType_clients();
}

@GetMapping(path="/type_client/{id}")
public Optional<Type_client> getType_client(@PathVariable Long id){
	return type_clientService.getType_client(id);
}

@PostMapping(path="/type_client")
public Type_client addType_client(@RequestBody Type_client type_client){
	return type_clientService.addType_client(type_client);
}

@PutMapping(path="/type_client/{id}")
public Type_client updateType_client(@PathVariable Long id, @RequestBody Type_client type_client){
	return type_clientService.updateType_client(id, type_client);
}

@DeleteMapping(path="/type_client/{id}")
public void deleteType_client(@PathVariable Long id){
	type_clientService.deleteType_client(id);
}

}
