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

import net.olea.santemaghreb.entities.Profession;
import net.olea.santemaghreb.services.ProfessionService;

@RestController
@RequestMapping(path="/api/")
public class ProfessionController {

public ProfessionService professionService;

public ProfessionController(ProfessionService professionService) {
	this.professionService = professionService;
}

@GetMapping(path="/professions")
public List<Profession> getProfessions(){
	return professionService.getProfessions();
}

@GetMapping(path="/profession/{id}")
public Optional<Profession> getProfession(@PathVariable Long id){
	return professionService.getProfession(id);
}

@PostMapping(path="/profession")
public Profession addProfession(@RequestBody Profession profession){
	return professionService.addProfession(profession);
}

@PutMapping(path="/profession/{id}")
public Profession updateProfession(@PathVariable Long id, @RequestBody Profession profession){
	return professionService.updateProfession(id, profession);
}

@DeleteMapping(path="/profession/{id}")
public void deleteProfession(@PathVariable Long id){
	professionService.deleteProfession(id);
}

}
