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

import net.olea.santemaghreb.entities.Civilite;
import net.olea.santemaghreb.services.CiviliteService;

@RestController
@RequestMapping(path="/api/")
public class CiviliteController {

public CiviliteService civiliteService;

public CiviliteController(CiviliteService civiliteService) {
	this.civiliteService = civiliteService;
}

@GetMapping(path="/civilites")
public List<Civilite> getCivilites(){
	return civiliteService.getCivilites();
}

@GetMapping(path="/civilite/{id}")
public Optional<Civilite> getCivilite(@PathVariable Long id){
	return civiliteService.getCivilite(id);
}

@PostMapping(path="/civilite")
public Civilite addCivilite(@RequestBody Civilite civilite){
	return civiliteService.addCivilite(civilite);
}

@PutMapping(path="/civilite/{id}")
public Civilite updateCivilite(@PathVariable Long id, @RequestBody Civilite civilite){
	return civiliteService.updateCivilite(id, civilite);
}

@DeleteMapping(path="/civilite/{id}")
public void deleteCivilite(@PathVariable Long id){
	civiliteService.deleteCivilite(id);
}

}
