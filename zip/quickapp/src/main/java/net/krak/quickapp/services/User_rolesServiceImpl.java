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

import net.krak.quickapp.entities.User_roles;

import net.krak.quickapp.entities.User_roles;

import net.krak.quickapp.repositories.User_rolesRepository;

@Service
@Transactional
public class User_rolesServiceImpl implements User_rolesService {

private User_rolesRepository user_rolesRepository;

public User_rolesServiceImpl(User_rolesRepository user_rolesRepository) {
	this.user_rolesRepository = user_rolesRepository;
}

@Override
public List<User_roles> getUser_roless(){
	return user_rolesRepository.findAll();
}

@Override
public Optional<User_roles> getUser_roles(Long id){
	return user_rolesRepository.findById(id);
}

@Override
public User_roles addUser_roles(User_roles user_roles){
	user_roles.setStatut(Statut.ACTIVE);
	user_roles.setDate_creation(Instant.now());
	user_roles.setDate_modification(Instant.now());
	return user_rolesRepository.save(user_roles);
}

@Override
public User_roles updateUser_roles(Long id, User_roles user_roles_new_data){
	Optional<User_roles> otptionalUser_roles_old  = user_rolesRepository.findById(id);
	User_roles user_roles_old = otptionalUser_roles_old.get();

	user_roles_new_data.setStatut(user_roles_old.getStatut());
	user_roles_new_data.setDate_creation(user_roles_old.getDate_creation());
	user_roles_new_data.setDate_modification(Instant.now());

	return user_rolesRepository.save(user_roles_new_data);
}

@Override
public void deleteUser_roles(Long id){
	Optional<User_roles> otptionalUser_roles = user_rolesRepository.findById(id);
	User_roles user_roles = otptionalUser_roles.get();

	user_roles.setStatut(user_roles.getStatut());
	user_roles.setDate_modification(Instant.now());

	user_rolesRepository.deleteById(id);
}

}
