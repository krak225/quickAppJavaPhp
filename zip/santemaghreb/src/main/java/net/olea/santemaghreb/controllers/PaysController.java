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

import net.olea.santemaghreb.entities.Pays;
import net.olea.santemaghreb.services.PaysService;

@RestController
@RequestMapping(path="/api/")
public class PaysController {

public PaysService paysService;

public PaysController(PaysService paysService) {
	this.paysService = paysService;
}

@GetMapping(path="/payss")
public List<Pays> getPayss(){
	return paysService.getPayss();
}

@GetMapping(path="/pays/{id}")
public Optional<Pays> getPays(@PathVariable Long id){
	return paysService.getPays(id);
}

@PostMapping(path="/pays")
public Pays addPays(@RequestBody Pays pays){
	return paysService.addPays(pays);
}

@PutMapping(path="/pays/{id}")
public Pays updatePays(@PathVariable Long id, @RequestBody Pays pays){
	return paysService.updatePays(id, pays);
}

@DeleteMapping(path="/pays/{id}")
public void deletePays(@PathVariable Long id){
	paysService.deletePays(id);
}

}
