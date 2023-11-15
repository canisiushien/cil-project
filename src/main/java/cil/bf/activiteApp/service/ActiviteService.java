/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service;

import cil.bf.activiteApp.service.dto.ActionDTO;
import cil.bf.activiteApp.service.dto.ActiviteDTO;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface ActiviteService {

    ActiviteDTO create(ActiviteDTO activiteDTO);

    ActiviteDTO update(ActiviteDTO activiteDTO);

    Optional<ActiviteDTO> getById(Long id);

    Page<ActiviteDTO> findAll(Pageable pageable);

    List<ActiviteDTO> findAll();

    void delete(Long id);

    ActiviteDTO cloturer(Long id, MultipartFile file);

}
