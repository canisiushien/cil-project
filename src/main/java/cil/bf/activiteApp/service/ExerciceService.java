package cil.bf.activiteApp.service;

import cil.bf.activiteApp.service.dto.ExerciceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Created by Zak TEGUERA on 14/09/2023.
 * <teguera.zakaria@gmail.com>
 */
public interface ExerciceService {

    ExerciceDTO create(ExerciceDTO exercice);

    ExerciceDTO update(ExerciceDTO exercice);

    Optional<ExerciceDTO> getById(Long id);

    Page<ExerciceDTO> findAll(Pageable pageable);

    List<ExerciceDTO> findAll();

    void delete(Long id);
}
