package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.TypeIndicateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeIndicateurRepository extends JpaRepository<TypeIndicateur, Long> {
}
