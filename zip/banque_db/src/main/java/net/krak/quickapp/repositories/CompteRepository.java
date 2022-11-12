package net.krak.quickapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import net.krak.quickapp.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {

}
