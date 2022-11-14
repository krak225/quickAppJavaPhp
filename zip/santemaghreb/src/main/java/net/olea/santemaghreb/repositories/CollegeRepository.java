package net.olea.santemaghreb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import net.olea.santemaghreb.entities.College;

public interface CollegeRepository extends JpaRepository<College, Long> {

}
