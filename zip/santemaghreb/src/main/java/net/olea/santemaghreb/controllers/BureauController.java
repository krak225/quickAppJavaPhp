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

import net.olea.santemaghreb.entities.Bureau;
import net.olea.santemaghreb.services.BureauService;

@RestController
@RequestMapping(path="/api/")
public class BureauController {

public BureauService bureauService;

public BureauController(BureauService bureauService) {
	this.bureauService = bureauService;
}

@GetMapping(path="/bureaus")
public List<Bureau> getBureaus(){
	return bureauService.getBureaus();
}

@GetMapping(path="/bureau/{id}")
public Optional<Bureau> getBureau(@PathVariable Long id){
	return bureauService.getBureau(id);
}

@PostMapping(path="/bureau")
public Bureau addBureau(@RequestBody Bureau bureau){
	return bureauService.addBureau(bureau);
}

@PutMapping(path="/bureau/{id}")
public Bureau updateBureau(@PathVariable Long id, @RequestBody Bureau bureau){
	return bureauService.updateBureau(id, bureau);
}

@DeleteMapping(path="/bureau/{id}")
public void deleteBureau(@PathVariable Long id){
	bureauService.deleteBureau(id);
}

}
