package net.olea.santemaghreb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import net.olea.santemaghreb.entities.Mouvement;

public interface MouvementRepository extends JpaRepository<Mouvement, Long> {

}
