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

import net.krak.quickapp.entities.Role;
import net.krak.quickapp.services.RoleService;

@RestController
@RequestMapping(path="/api/")
public class RoleController {

public RoleService roleService;

public RoleController(RoleService roleService) {
	this.roleService = roleService;
}

@GetMapping(path="/roles")
public List<Role> getRoles(){
	return roleService.getRoles();
}

@GetMapping(path="/role/{id}")
public Optional<Role> getRole(@PathVariable Long id){
	return roleService.getRole(id);
}

@PostMapping(path="/role")
public Role addRole(@RequestBody Role role){
	return roleService.addRole(role);
}

@PutMapping(path="/role/{id}")
public Role updateRole(@PathVariable Long id, @RequestBody Role role){
	return roleService.updateRole(id, role);
}

@DeleteMapping(path="/role/{id}")
public void deleteRole(@PathVariable Long id){
	roleService.deleteRole(id);
}

}
