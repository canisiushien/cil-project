package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.Fonction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FonctionRepository extends JpaRepository<Fonction, Long> {
}
