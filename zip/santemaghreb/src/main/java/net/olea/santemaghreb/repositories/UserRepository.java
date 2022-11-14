package net.olea.santemaghreb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.olea.santemaghreb.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
