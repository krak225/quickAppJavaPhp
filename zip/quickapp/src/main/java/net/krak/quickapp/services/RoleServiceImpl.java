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

import net.krak.quickapp.entities.Role;

import net.krak.quickapp.entities.Role;

import net.krak.quickapp.repositories.RoleRepository;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

private RoleRepository roleRepository;

public RoleServiceImpl(RoleRepository roleRepository) {
	this.roleRepository = roleRepository;
}

@Override
public List<Role> getRoles(){
	return roleRepository.findAll();
}

@Override
public Optional<Role> getRole(Long id){
	return roleRepository.findById(id);
}

@Override
public Role addRole(Role role){
	role.setStatut(Statut.ACTIVE);
	role.setDate_creation(Instant.now());
	role.setDate_modification(Instant.now());
	return roleRepository.save(role);
}

@Override
public Role updateRole(Long id, Role role_new_data){
	Optional<Role> otptionalRole_old  = roleRepository.findById(id);
	Role role_old = otptionalRole_old.get();

	role_new_data.setStatut(role_old.getStatut());
	role_new_data.setDate_creation(role_old.getDate_creation());
	role_new_data.setDate_modification(Instant.now());

	return roleRepository.save(role_new_data);
}

@Override
public void deleteRole(Long id){
	Optional<Role> otptionalRole = roleRepository.findById(id);
	Role role = otptionalRole.get();

	role.setStatut(role.getStatut());
	role.setDate_modification(Instant.now());

	roleRepository.deleteById(id);
}

}
