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

import net.olea.santemaghreb.entities.Devise;
import net.olea.santemaghreb.services.DeviseService;

@RestController
@RequestMapping(path="/api/")
public class DeviseController {

public DeviseService deviseService;

public DeviseController(DeviseService deviseService) {
	this.deviseService = deviseService;
}

@GetMapping(path="/devises")
public List<Devise> getDevises(){
	return deviseService.getDevises();
}

@GetMapping(path="/devise/{id}")
public Optional<Devise> getDevise(@PathVariable Long id){
	return deviseService.getDevise(id);
}

@PostMapping(path="/devise")
public Devise addDevise(@RequestBody Devise devise){
	return deviseService.addDevise(devise);
}

@PutMapping(path="/devise/{id}")
public Devise updateDevise(@PathVariable Long id, @RequestBody Devise devise){
	return deviseService.updateDevise(id, devise);
}

@DeleteMapping(path="/devise/{id}")
public void deleteDevise(@PathVariable Long id){
	deviseService.deleteDevise(id);
}

}
