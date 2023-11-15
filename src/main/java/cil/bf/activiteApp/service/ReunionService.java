/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service;

import cil.bf.activiteApp.service.dto.ReunionDTO;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface ReunionService {

    ReunionDTO create(ReunionDTO r);

    ReunionDTO update(ReunionDTO r);

    Optional<ReunionDTO> getById(Long id);

    Page<ReunionDTO> findAll(Pageable pageable);

    List<ReunionDTO> findAll();

    void delete(Long id);

    List<ReunionDTO> findByTypeReunion(Long id);

    ReunionDTO reprogrammer(ReunionDTO r);

    ReunionDTO validerReunion(ReunionDTO request);

    ReunionDTO annulerReunion(Long id, String statut);

    ReunionDTO cloturer(Long id, MultipartFile file);
}
