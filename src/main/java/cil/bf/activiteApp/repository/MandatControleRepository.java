package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.MandatControle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MandatControleRepository extends JpaRepository<MandatControle, Long> {
}
