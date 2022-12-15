package net.krak.grh.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.krak.grh.entities.Role;
import net.krak.grh.entities.User;
import net.krak.grh.playload.requests.RoleUserForm;
import net.krak.grh.services.AccountService;


@RestController
@RequestMapping(path="/api/")
public class AccountController {
		
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	private AccountService accountService;

	@GetMapping(path="/users")
	public List<User> users(){
		
		return accountService.listUsers();
		
	}
	
	@GetMapping("/users/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
		
        return accountService.getUser(id);
        
    }

	@PostMapping(path="/users/add")
	@PostAuthorize("hasAuthority('ADMIN')")
	public User saveUser(@RequestBody User user){
		
		return accountService.AddUser(user);
	}


	@GetMapping(path="/roles")
	public List<Role> roles(){
		
		return accountService.listRoles();
		
	}
	
	@PostMapping(path="/roles/add")
	public Role saveRole(@RequestBody Role role){
		
		return accountService.AddRole(role);
		
	}

	@PostMapping(path="/addroletouser")
	public void AddRoleToUser(@RequestBody RoleUserForm roleUserForm){
		
		accountService.AddRoleToUser(roleUserForm.username, roleUserForm.roleName);
		
	}

	@GetMapping(path="/profile")
	public User profile(Principal principal){
		
		return accountService.loadUserByUsername(principal.getName());
		
	}
	
}


