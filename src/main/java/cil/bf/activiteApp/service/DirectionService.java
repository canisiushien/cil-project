/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service;

import cil.bf.activiteApp.service.dto.DirectionDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface DirectionService {

    DirectionDTO create(DirectionDTO directionDTO);

    DirectionDTO update(DirectionDTO directionDTO);

    Optional<DirectionDTO> getById(Long id);

    Page<DirectionDTO> findAll(Pageable pageable);

    List<DirectionDTO> findAll();

    void delete(Long id);
}
