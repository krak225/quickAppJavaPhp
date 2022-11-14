package net.olea.santemaghreb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.olea.santemaghreb.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByRoleName(String roleName);
}
