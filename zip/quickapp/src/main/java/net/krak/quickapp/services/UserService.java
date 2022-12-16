package net.krak.quickapp.services;

import java.util.List;
import java.util.Optional;

import net.krak.quickapp.entities.User;

public interface UserService {

	List<User> getUsers();
	Optional<User> getUser(Long id);
	User addUser(User user);
	User updateUser(Long id, User user);
	void deleteUser(Long id);

}
