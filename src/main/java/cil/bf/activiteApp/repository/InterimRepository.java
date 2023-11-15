package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.Action;
import cil.bf.activiteApp.domain.Interim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterimRepository extends JpaRepository<Interim, Long> {
}
