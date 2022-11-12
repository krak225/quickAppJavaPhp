package net.krak.quickapp.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.krak.quickapp.entities.Role;
import net.krak.quickapp.entities.User;
import net.krak.quickapp.repositories.RoleRepository;
import net.krak.quickapp.repositories.UserRepository;


@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	
	public AccountServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public User AddUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		return userRepository.save(user);
	}

	@Override
	public Role AddRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void AddRoleToUser(String username, String roleName) {
		User user = userRepository.findByUsername(username);
		Role role = roleRepository.findByRoleName(roleName);
		
		user.getRoles().add(role);
	}

	@Override
	public User loadUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> listUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<Role> listRoles() {
		return roleRepository.findAll();
	}

	@Override
	public Optional<User> getUser(Long id) {
		return userRepository.findById(id);
	}

	
}
