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

import net.olea.santemaghreb.entities.Mode_reglement;
import net.olea.santemaghreb.services.Mode_reglementService;

@RestController
@RequestMapping(path="/api/")
public class Mode_reglementController {

public Mode_reglementService mode_reglementService;

public Mode_reglementController(Mode_reglementService mode_reglementService) {
	this.mode_reglementService = mode_reglementService;
}

@GetMapping(path="/mode_reglements")
public List<Mode_reglement> getMode_reglements(){
	return mode_reglementService.getMode_reglements();
}

@GetMapping(path="/mode_reglement/{id}")
public Optional<Mode_reglement> getMode_reglement(@PathVariable Long id){
	return mode_reglementService.getMode_reglement(id);
}

@PostMapping(path="/mode_reglement")
public Mode_reglement addMode_reglement(@RequestBody Mode_reglement mode_reglement){
	return mode_reglementService.addMode_reglement(mode_reglement);
}

@PutMapping(path="/mode_reglement/{id}")
public Mode_reglement updateMode_reglement(@PathVariable Long id, @RequestBody Mode_reglement mode_reglement){
	return mode_reglementService.updateMode_reglement(id, mode_reglement);
}

@DeleteMapping(path="/mode_reglement/{id}")
public void deleteMode_reglement(@PathVariable Long id){
	mode_reglementService.deleteMode_reglement(id);
}

}
