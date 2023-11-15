package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.Exercice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Zak TEGUERA on 14/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@Repository
public interface ExerciceRepository extends JpaRepository<Exercice, Long> {
}
