package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.Programme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammeRepository extends JpaRepository<Programme, Long> {
}
