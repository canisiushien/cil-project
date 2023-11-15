/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service;

import cil.bf.activiteApp.service.dto.ActionDTO;
import cil.bf.activiteApp.service.dto.TypeSessionDTO;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface TypeSessionService {

    TypeSessionDTO create(TypeSessionDTO t);

    TypeSessionDTO update(TypeSessionDTO t);

    Optional<TypeSessionDTO> getById(Long id);

    Page<TypeSessionDTO> findAll(Pageable pageable);

    List<TypeSessionDTO> findAll();

    void delete(Long id);
}
