package net.olea.santemaghreb.services;

import java.util.List;
import java.util.Optional;

import net.olea.santemaghreb.entities.Role;
import net.olea.santemaghreb.entities.User;


public interface AccountService{
	User AddUser(User user);
	Role AddRole(Role role);
	void AddRoleToUser(String username, String roleName);
	User loadUserByUsername(String username);
	List<User> listUsers();
	List<Role> listRoles();
	Optional<User> getUser(Long id);
	
}

