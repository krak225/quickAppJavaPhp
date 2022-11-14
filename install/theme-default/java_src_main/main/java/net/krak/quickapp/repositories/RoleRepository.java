package net.krak.quickapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.krak.quickapp.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRoleName(String roleName);
}
