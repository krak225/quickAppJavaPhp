package net.krak.grh.services;

import java.util.List;
import java.util.Optional;

import net.krak.grh.entities.Role;
import net.krak.grh.entities.User;


public interface AccountService{
	User AddUser(User user);
	Role AddRole(Role role);
	void AddRoleToUser(String username, String roleName);
	User loadUserByUsername(String username);
	List<User> listUsers();
	List<Role> listRoles();
	Optional<User> getUser(Long id);
	
}

