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

import net.olea.santemaghreb.entities.Motif;
import net.olea.santemaghreb.services.MotifService;

@RestController
@RequestMapping(path="/api/")
public class MotifController {

public MotifService motifService;

public MotifController(MotifService motifService) {
	this.motifService = motifService;
}

@GetMapping(path="/motifs")
public List<Motif> getMotifs(){
	return motifService.getMotifs();
}

@GetMapping(path="/motif/{id}")
public Optional<Motif> getMotif(@PathVariable Long id){
	return motifService.getMotif(id);
}

@PostMapping(path="/motif")
public Motif addMotif(@RequestBody Motif motif){
	return motifService.addMotif(motif);
}

@PutMapping(path="/motif/{id}")
public Motif updateMotif(@PathVariable Long id, @RequestBody Motif motif){
	return motifService.updateMotif(id, motif);
}

@DeleteMapping(path="/motif/{id}")
public void deleteMotif(@PathVariable Long id){
	motifService.deleteMotif(id);
}

}
