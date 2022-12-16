package net.krak.quickapp.services;

import java.util.List;
import java.util.Optional;

import net.krak.quickapp.entities.Role;
import net.krak.quickapp.entities.User;


public interface AccountService{
	User AddUser(User user);
	Role AddRole(Role role);
	void AddRoleToUser(String username, String roleName);
	User loadUserByUsername(String username);
	List<User> listUsers();
	List<Role> listRoles();
	Optional<User> getUser(Long id);
	
}

