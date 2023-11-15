package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.Unite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniteRepository extends JpaRepository<Unite, Long> {
}
