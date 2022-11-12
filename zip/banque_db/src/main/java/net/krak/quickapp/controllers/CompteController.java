package net.krak.quickapp.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.krak.quickapp.entities.Compte;
import net.krak.quickapp.services.CompteService;

@RestController
@RequestMapping(path="/api/")
public class CompteController {

public CompteService compteService;

public CompteController(CompteService compteService) {
	this.compteService = compteService;
}

@GetMapping(path="/comptes")
public List<Compte> getComptes(){
	return compteService.findAll();
}

@GetMapping(path="/compte/{id}")
public Compte getCompte(@PathVariable Long id){
	return compteService.findById(id);
}

@PostMapping(path="/compte")
public Compte AddCompte(Compte compte){
	return compteService.save(compte);
}

@PutMapping(path="/compte/{id}")
public Compte EditCompte(@PathVariable Long id, Compte compte){
	return compteService.save(compte);
}

@DeleteMapping(path="/compte/{id}")
public Compte DeleteCompte(@PathVariable Long id){
	return compteService.delete(id);
}

}
