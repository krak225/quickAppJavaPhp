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

import net.olea.santemaghreb.entities.Compagnie;
import net.olea.santemaghreb.services.CompagnieService;

@RestController
@RequestMapping(path="/api/")
public class CompagnieController {

public CompagnieService compagnieService;

public CompagnieController(CompagnieService compagnieService) {
	this.compagnieService = compagnieService;
}

@GetMapping(path="/compagnies")
public List<Compagnie> getCompagnies(){
	return compagnieService.getCompagnies();
}

@GetMapping(path="/compagnie/{id}")
public Optional<Compagnie> getCompagnie(@PathVariable Long id){
	return compagnieService.getCompagnie(id);
}

@PostMapping(path="/compagnie")
public Compagnie addCompagnie(@RequestBody Compagnie compagnie){
	return compagnieService.addCompagnie(compagnie);
}

@PutMapping(path="/compagnie/{id}")
public Compagnie updateCompagnie(@PathVariable Long id, @RequestBody Compagnie compagnie){
	return compagnieService.updateCompagnie(id, compagnie);
}

@DeleteMapping(path="/compagnie/{id}")
public void deleteCompagnie(@PathVariable Long id){
	compagnieService.deleteCompagnie(id);
}

}
