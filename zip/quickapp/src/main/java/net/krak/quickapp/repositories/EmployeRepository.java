package net.krak.quickapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import net.krak.quickapp.entities.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Long> {

}
