package cil.bf.activiteApp.repository;

import cil.bf.activiteApp.domain.NumeroGeneretor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface NumeroGeneretorRepository extends JpaRepository<NumeroGeneretor, Long> {
    Optional<NumeroGeneretor> findByTypeNumeroAndAnnee(Integer typeNumero, Integer integer);

    NumeroGeneretor getById(Long id);
}