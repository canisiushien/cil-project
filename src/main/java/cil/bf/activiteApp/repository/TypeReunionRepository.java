package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.TypeReunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeReunionRepository extends JpaRepository<TypeReunion, Long> {
}
