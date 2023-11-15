package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.ObjectifStrategique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectifStrategiqueRepository extends JpaRepository<ObjectifStrategique, Long> {
}
