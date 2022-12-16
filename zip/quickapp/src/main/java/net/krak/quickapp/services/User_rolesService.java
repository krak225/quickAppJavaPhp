package net.krak.quickapp.services;

import java.util.List;
import java.util.Optional;

import net.krak.quickapp.entities.User_roles;

public interface User_rolesService {

	List<User_roles> getUser_roless();
	Optional<User_roles> getUser_roles(Long id);
	User_roles addUser_roles(User_roles user_roles);
	User_roles updateUser_roles(Long id, User_roles user_roles);
	void deleteUser_roles(Long id);

}
