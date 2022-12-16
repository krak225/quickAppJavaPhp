package net.krak.quickapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import net.krak.quickapp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
