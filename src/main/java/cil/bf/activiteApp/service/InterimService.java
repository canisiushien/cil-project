/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service;

import cil.bf.activiteApp.service.dto.ActionDTO;
import cil.bf.activiteApp.service.dto.InterimDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface InterimService {

    InterimDTO create(InterimDTO interimDTO);

    InterimDTO update(InterimDTO interimDTO);

    Optional<InterimDTO> getById(Long id);

    Page<InterimDTO> findAll(Pageable pageable);

    List<InterimDTO> findAll();

    void delete(Long id);
}
