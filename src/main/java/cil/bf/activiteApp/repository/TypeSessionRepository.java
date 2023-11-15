package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.TypeSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeSessionRepository extends JpaRepository<TypeSession, Long> {
}
