/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service;

import cil.bf.activiteApp.service.dto.ActionDTO;
import cil.bf.activiteApp.service.dto.MandatControleDTO;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface MandatControleService {

    MandatControleDTO create(MandatControleDTO mandatControleDTO);

    MandatControleDTO update(MandatControleDTO mandatControleDTO);

    Optional<MandatControleDTO> getById(Long id);

    Page<MandatControleDTO> findAll(Pageable pageable);

    List<MandatControleDTO> findAll();

    void delete(Long id);

}
