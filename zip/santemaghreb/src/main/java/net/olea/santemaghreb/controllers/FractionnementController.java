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

import net.olea.santemaghreb.entities.Fractionnement;
import net.olea.santemaghreb.services.FractionnementService;

@RestController
@RequestMapping(path="/api/")
public class FractionnementController {

public FractionnementService fractionnementService;

public FractionnementController(FractionnementService fractionnementService) {
	this.fractionnementService = fractionnementService;
}

@GetMapping(path="/fractionnements")
public List<Fractionnement> getFractionnements(){
	return fractionnementService.getFractionnements();
}

@GetMapping(path="/fractionnement/{id}")
public Optional<Fractionnement> getFractionnement(@PathVariable Long id){
	return fractionnementService.getFractionnement(id);
}

@PostMapping(path="/fractionnement")
public Fractionnement addFractionnement(@RequestBody Fractionnement fractionnement){
	return fractionnementService.addFractionnement(fractionnement);
}

@PutMapping(path="/fractionnement/{id}")
public Fractionnement updateFractionnement(@PathVariable Long id, @RequestBody Fractionnement fractionnement){
	return fractionnementService.updateFractionnement(id, fractionnement);
}

@DeleteMapping(path="/fractionnement/{id}")
public void deleteFractionnement(@PathVariable Long id){
	fractionnementService.deleteFractionnement(id);
}

}
