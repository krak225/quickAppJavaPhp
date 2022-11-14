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

import net.olea.santemaghreb.entities.Qualite_beneficiaire;
import net.olea.santemaghreb.services.Qualite_beneficiaireService;

@RestController
@RequestMapping(path="/api/")
public class Qualite_beneficiaireController {

public Qualite_beneficiaireService qualite_beneficiaireService;

public Qualite_beneficiaireController(Qualite_beneficiaireService qualite_beneficiaireService) {
	this.qualite_beneficiaireService = qualite_beneficiaireService;
}

@GetMapping(path="/qualite_beneficiaires")
public List<Qualite_beneficiaire> getQualite_beneficiaires(){
	return qualite_beneficiaireService.getQualite_beneficiaires();
}

@GetMapping(path="/qualite_beneficiaire/{id}")
public Optional<Qualite_beneficiaire> getQualite_beneficiaire(@PathVariable Long id){
	return qualite_beneficiaireService.getQualite_beneficiaire(id);
}

@PostMapping(path="/qualite_beneficiaire")
public Qualite_beneficiaire addQualite_beneficiaire(@RequestBody Qualite_beneficiaire qualite_beneficiaire){
	return qualite_beneficiaireService.addQualite_beneficiaire(qualite_beneficiaire);
}

@PutMapping(path="/qualite_beneficiaire/{id}")
public Qualite_beneficiaire updateQualite_beneficiaire(@PathVariable Long id, @RequestBody Qualite_beneficiaire qualite_beneficiaire){
	return qualite_beneficiaireService.updateQualite_beneficiaire(id, qualite_beneficiaire);
}

@DeleteMapping(path="/qualite_beneficiaire/{id}")
public void deleteQualite_beneficiaire(@PathVariable Long id){
	qualite_beneficiaireService.deleteQualite_beneficiaire(id);
}

}
