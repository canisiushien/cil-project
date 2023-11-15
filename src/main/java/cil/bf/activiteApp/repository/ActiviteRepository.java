package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Long> {
}
