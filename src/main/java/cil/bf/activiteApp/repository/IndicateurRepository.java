package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.Indicateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicateurRepository extends JpaRepository<Indicateur, Long> {
}
