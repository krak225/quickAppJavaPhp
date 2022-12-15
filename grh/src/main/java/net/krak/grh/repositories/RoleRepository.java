package net.krak.grh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.krak.grh.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRoleName(String roleName);
}
