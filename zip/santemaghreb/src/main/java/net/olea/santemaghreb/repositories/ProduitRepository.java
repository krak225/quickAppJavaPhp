package net.olea.santemaghreb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import net.olea.santemaghreb.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

}
