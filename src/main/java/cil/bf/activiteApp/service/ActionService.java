/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service;

import cil.bf.activiteApp.service.dto.ActionDTO;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface ActionService {

    ActionDTO create(ActionDTO actionDTO);

    ActionDTO update(ActionDTO actionDTO);

    Optional<ActionDTO> getById(Long id);

    Page<ActionDTO> findAll(Pageable pageable);

    List<ActionDTO> findAll();

    void delete(Long id);
}
