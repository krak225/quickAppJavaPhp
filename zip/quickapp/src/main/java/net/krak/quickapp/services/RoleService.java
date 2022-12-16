package net.krak.quickapp.services;

import java.util.List;
import java.util.Optional;

import net.krak.quickapp.entities.Role;

public interface RoleService {

	List<Role> getRoles();
	Optional<Role> getRole(Long id);
	Role addRole(Role role);
	Role updateRole(Long id, Role role);
	void deleteRole(Long id);

}
