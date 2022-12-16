package net.krak.quickapp.controllers;

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

import net.krak.quickapp.entities.Employe;
import net.krak.quickapp.services.EmployeService;

@RestController
@RequestMapping(path="/api/")
public class EmployeController {

public EmployeService employeService;

public EmployeController(EmployeService employeService) {
	this.employeService = employeService;
}

@GetMapping(path="/employes")
public List<Employe> getEmployes(){
	return employeService.getEmployes();
}

@GetMapping(path="/employe/{id}")
public Optional<Employe> getEmploye(@PathVariable Long id){
	return employeService.getEmploye(id);
}

@PostMapping(path="/employe")
public Employe addEmploye(@RequestBody Employe employe){
	return employeService.addEmploye(employe);
}

@PutMapping(path="/employe/{id}")
public Employe updateEmploye(@PathVariable Long id, @RequestBody Employe employe){
	return employeService.updateEmploye(id, employe);
}

@DeleteMapping(path="/employe/{id}")
public void deleteEmploye(@PathVariable Long id){
	employeService.deleteEmploye(id);
}

}
