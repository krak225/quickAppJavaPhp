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

import net.olea.santemaghreb.entities.Mouvement;
import net.olea.santemaghreb.services.MouvementService;

@RestController
@RequestMapping(path="/api/")
public class MouvementController {

public MouvementService mouvementService;

public MouvementController(MouvementService mouvementService) {
	this.mouvementService = mouvementService;
}

@GetMapping(path="/mouvements")
public List<Mouvement> getMouvements(){
	return mouvementService.getMouvements();
}

@GetMapping(path="/mouvement/{id}")
public Optional<Mouvement> getMouvement(@PathVariable Long id){
	return mouvementService.getMouvement(id);
}

@PostMapping(path="/mouvement")
public Mouvement addMouvement(@RequestBody Mouvement mouvement){
	return mouvementService.addMouvement(mouvement);
}

@PutMapping(path="/mouvement/{id}")
public Mouvement updateMouvement(@PathVariable Long id, @RequestBody Mouvement mouvement){
	return mouvementService.updateMouvement(id, mouvement);
}

@DeleteMapping(path="/mouvement/{id}")
public void deleteMouvement(@PathVariable Long id){
	mouvementService.deleteMouvement(id);
}

}
