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

import net.olea.santemaghreb.entities.Mouvement_motif;
import net.olea.santemaghreb.services.Mouvement_motifService;

@RestController
@RequestMapping(path="/api/")
public class Mouvement_motifController {

public Mouvement_motifService mouvement_motifService;

public Mouvement_motifController(Mouvement_motifService mouvement_motifService) {
	this.mouvement_motifService = mouvement_motifService;
}

@GetMapping(path="/mouvement_motifs")
public List<Mouvement_motif> getMouvement_motifs(){
	return mouvement_motifService.getMouvement_motifs();
}

@GetMapping(path="/mouvement_motif/{id}")
public Optional<Mouvement_motif> getMouvement_motif(@PathVariable Long id){
	return mouvement_motifService.getMouvement_motif(id);
}

@PostMapping(path="/mouvement_motif")
public Mouvement_motif addMouvement_motif(@RequestBody Mouvement_motif mouvement_motif){
	return mouvement_motifService.addMouvement_motif(mouvement_motif);
}

@PutMapping(path="/mouvement_motif/{id}")
public Mouvement_motif updateMouvement_motif(@PathVariable Long id, @RequestBody Mouvement_motif mouvement_motif){
	return mouvement_motifService.updateMouvement_motif(id, mouvement_motif);
}

@DeleteMapping(path="/mouvement_motif/{id}")
public void deleteMouvement_motif(@PathVariable Long id){
	mouvement_motifService.deleteMouvement_motif(id);
}

}
