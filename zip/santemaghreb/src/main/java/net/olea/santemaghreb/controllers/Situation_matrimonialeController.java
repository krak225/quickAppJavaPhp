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

import net.olea.santemaghreb.entities.Situation_matrimoniale;
import net.olea.santemaghreb.services.Situation_matrimonialeService;

@RestController
@RequestMapping(path="/api/")
public class Situation_matrimonialeController {

public Situation_matrimonialeService situation_matrimonialeService;

public Situation_matrimonialeController(Situation_matrimonialeService situation_matrimonialeService) {
	this.situation_matrimonialeService = situation_matrimonialeService;
}

@GetMapping(path="/situation_matrimoniales")
public List<Situation_matrimoniale> getSituation_matrimoniales(){
	return situation_matrimonialeService.getSituation_matrimoniales();
}

@GetMapping(path="/situation_matrimoniale/{id}")
public Optional<Situation_matrimoniale> getSituation_matrimoniale(@PathVariable Long id){
	return situation_matrimonialeService.getSituation_matrimoniale(id);
}

@PostMapping(path="/situation_matrimoniale")
public Situation_matrimoniale addSituation_matrimoniale(@RequestBody Situation_matrimoniale situation_matrimoniale){
	return situation_matrimonialeService.addSituation_matrimoniale(situation_matrimoniale);
}

@PutMapping(path="/situation_matrimoniale/{id}")
public Situation_matrimoniale updateSituation_matrimoniale(@PathVariable Long id, @RequestBody Situation_matrimoniale situation_matrimoniale){
	return situation_matrimonialeService.updateSituation_matrimoniale(id, situation_matrimoniale);
}

@DeleteMapping(path="/situation_matrimoniale/{id}")
public void deleteSituation_matrimoniale(@PathVariable Long id){
	situation_matrimonialeService.deleteSituation_matrimoniale(id);
}

}
