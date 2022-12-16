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

import net.krak.quickapp.entities.User;
import net.krak.quickapp.services.UserService;

@RestController
@RequestMapping(path="/api/")
public class UserController {

public UserService userService;

public UserController(UserService userService) {
	this.userService = userService;
}

@GetMapping(path="/users")
public List<User> getUsers(){
	return userService.getUsers();
}

@GetMapping(path="/user/{id}")
public Optional<User> getUser(@PathVariable Long id){
	return userService.getUser(id);
}

@PostMapping(path="/user")
public User addUser(@RequestBody User user){
	return userService.addUser(user);
}

@PutMapping(path="/user/{id}")
public User updateUser(@PathVariable Long id, @RequestBody User user){
	return userService.updateUser(id, user);
}

@DeleteMapping(path="/user/{id}")
public void deleteUser(@PathVariable Long id){
	userService.deleteUser(id);
}

}
