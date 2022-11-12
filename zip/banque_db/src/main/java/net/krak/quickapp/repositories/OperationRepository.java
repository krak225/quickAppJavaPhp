package net.krak.quickapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import net.krak.quickapp.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {

}
