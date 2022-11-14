package net.olea.santemaghreb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import net.olea.santemaghreb.entities.Pays;

public interface PaysRepository extends JpaRepository<Pays, Long> {

}
