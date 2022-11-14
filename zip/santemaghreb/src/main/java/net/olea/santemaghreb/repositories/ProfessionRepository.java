package net.olea.santemaghreb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import net.olea.santemaghreb.entities.Profession;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {

}
