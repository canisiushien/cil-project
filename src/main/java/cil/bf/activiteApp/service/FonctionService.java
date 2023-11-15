/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service;

import cil.bf.activiteApp.service.dto.ExerciceDTO;
import cil.bf.activiteApp.service.dto.FonctionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 *
 * KABORE
 */
public interface FonctionService {

    FonctionDTO create(FonctionDTO fonctionDTO);

    FonctionDTO update(FonctionDTO fonctionDTO);

    Optional<FonctionDTO> getById(Long id);

    Page<FonctionDTO> findAll(Pageable pageable);
    List<FonctionDTO> findAll();

    void delete(Long id);
}
