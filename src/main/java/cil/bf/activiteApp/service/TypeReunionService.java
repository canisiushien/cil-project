/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service;

import cil.bf.activiteApp.service.dto.ActionDTO;
import cil.bf.activiteApp.service.dto.TypeReunionDTO;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface TypeReunionService {

    TypeReunionDTO create(TypeReunionDTO t);

    TypeReunionDTO update(TypeReunionDTO t);

    Optional<TypeReunionDTO> getById(Long id);

    Page<TypeReunionDTO> findAll(Pageable pageable);

    List<TypeReunionDTO> findAll();

    void delete(Long id);
}
