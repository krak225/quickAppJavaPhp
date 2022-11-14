package net.olea.santemaghreb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import net.olea.santemaghreb.entities.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {

}
