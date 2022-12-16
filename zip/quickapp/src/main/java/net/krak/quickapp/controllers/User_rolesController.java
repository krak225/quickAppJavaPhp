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

import net.krak.quickapp.entities.User_roles;
import net.krak.quickapp.services.User_rolesService;

@RestController
@RequestMapping(path="/api/")
public class User_rolesController {

public User_rolesService user_rolesService;

public User_rolesController(User_rolesService user_rolesService) {
	this.user_rolesService = user_rolesService;
}

@GetMapping(path="/user_roless")
public List<User_roles> getUser_roless(){
	return user_rolesService.getUser_roless();
}

@GetMapping(path="/user_roles/{id}")
public Optional<User_roles> getUser_roles(@PathVariable Long id){
	return user_rolesService.getUser_roles(id);
}

@PostMapping(path="/user_roles")
public User_roles addUser_roles(@RequestBody User_roles user_roles){
	return user_rolesService.addUser_roles(user_roles);
}

@PutMapping(path="/user_roles/{id}")
public User_roles updateUser_roles(@PathVariable Long id, @RequestBody User_roles user_roles){
	return user_rolesService.updateUser_roles(id, user_roles);
}

@DeleteMapping(path="/user_roles/{id}")
public void deleteUser_roles(@PathVariable Long id){
	user_rolesService.deleteUser_roles(id);
}

}
