package net.krak.quickapp.services;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import net.krak.quickapp.entities.Statut;

import net.krak.quickapp.entities.User;

import net.krak.quickapp.entities.User;

import net.krak.quickapp.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

private UserRepository userRepository;

public UserServiceImpl(UserRepository userRepository) {
	this.userRepository = userRepository;
}

@Override
public List<User> getUsers(){
	return userRepository.findAll();
}

@Override
public Optional<User> getUser(Long id){
	return userRepository.findById(id);
}

@Override
public User addUser(User user){
	user.setStatut(Statut.ACTIVE);
	user.setDate_creation(Instant.now());
	user.setDate_modification(Instant.now());
	return userRepository.save(user);
}

@Override
public User updateUser(Long id, User user_new_data){
	Optional<User> otptionalUser_old  = userRepository.findById(id);
	User user_old = otptionalUser_old.get();

	user_new_data.setStatut(user_old.getStatut());
	user_new_data.setDate_creation(user_old.getDate_creation());
	user_new_data.setDate_modification(Instant.now());

	return userRepository.save(user_new_data);
}

@Override
public void deleteUser(Long id){
	Optional<User> otptionalUser = userRepository.findById(id);
	User user = otptionalUser.get();

	user.setStatut(user.getStatut());
	user.setDate_modification(Instant.now());

	userRepository.deleteById(id);
}

}
