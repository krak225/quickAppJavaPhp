package net.krak.grh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.krak.grh.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
