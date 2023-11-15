/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service;

import cil.bf.activiteApp.service.dto.ActionDTO;
import cil.bf.activiteApp.service.dto.ProgrammeDTO;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface ProgrammeService {

    ProgrammeDTO create(ProgrammeDTO p);

    ProgrammeDTO update(ProgrammeDTO p);

    Optional<ProgrammeDTO> getById(Long id);

    Page<ProgrammeDTO> findAll(Pageable pageable);

    List<ProgrammeDTO> findAll();

    void delete(Long id);
}
