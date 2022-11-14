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

import net.olea.santemaghreb.entities.Duree;
import net.olea.santemaghreb.services.DureeService;

@RestController
@RequestMapping(path="/api/")
public class DureeController {

public DureeService dureeService;

public DureeController(DureeService dureeService) {
	this.dureeService = dureeService;
}

@GetMapping(path="/durees")
public List<Duree> getDurees(){
	return dureeService.getDurees();
}

@GetMapping(path="/duree/{id}")
public Optional<Duree> getDuree(@PathVariable Long id){
	return dureeService.getDuree(id);
}

@PostMapping(path="/duree")
public Duree addDuree(@RequestBody Duree duree){
	return dureeService.addDuree(duree);
}

@PutMapping(path="/duree/{id}")
public Duree updateDuree(@PathVariable Long id, @RequestBody Duree duree){
	return dureeService.updateDuree(id, duree);
}

@DeleteMapping(path="/duree/{id}")
public void deleteDuree(@PathVariable Long id){
	dureeService.deleteDuree(id);
}

}
