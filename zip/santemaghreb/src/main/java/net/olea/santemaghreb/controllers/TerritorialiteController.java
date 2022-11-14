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

import net.olea.santemaghreb.entities.Territorialite;
import net.olea.santemaghreb.services.TerritorialiteService;

@RestController
@RequestMapping(path="/api/")
public class TerritorialiteController {

public TerritorialiteService territorialiteService;

public TerritorialiteController(TerritorialiteService territorialiteService) {
	this.territorialiteService = territorialiteService;
}

@GetMapping(path="/territorialites")
public List<Territorialite> getTerritorialites(){
	return territorialiteService.getTerritorialites();
}

@GetMapping(path="/territorialite/{id}")
public Optional<Territorialite> getTerritorialite(@PathVariable Long id){
	return territorialiteService.getTerritorialite(id);
}

@PostMapping(path="/territorialite")
public Territorialite addTerritorialite(@RequestBody Territorialite territorialite){
	return territorialiteService.addTerritorialite(territorialite);
}

@PutMapping(path="/territorialite/{id}")
public Territorialite updateTerritorialite(@PathVariable Long id, @RequestBody Territorialite territorialite){
	return territorialiteService.updateTerritorialite(id, territorialite);
}

@DeleteMapping(path="/territorialite/{id}")
public void deleteTerritorialite(@PathVariable Long id){
	territorialiteService.deleteTerritorialite(id);
}

}
