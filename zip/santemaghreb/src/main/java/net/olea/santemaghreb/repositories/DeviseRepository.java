package net.olea.santemaghreb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import net.olea.santemaghreb.entities.Devise;

public interface DeviseRepository extends JpaRepository<Devise, Long> {

}
