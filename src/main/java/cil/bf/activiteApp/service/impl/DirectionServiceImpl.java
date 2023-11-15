/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.domain.Utilisateur;
import cil.bf.activiteApp.exception.CustomException;
import cil.bf.activiteApp.repository.DirectionRepository;
import cil.bf.activiteApp.repository.UtilisateurRepository;
import cil.bf.activiteApp.service.DirectionService;
import cil.bf.activiteApp.service.dto.DirectionDTO;
import cil.bf.activiteApp.service.mapper.DirectionMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@Service
public class DirectionServiceImpl implements DirectionService {

    private final DirectionRepository directionRepository;

    private final UtilisateurRepository utilisateurRepository;

    private final DirectionMapper directionMapper;

    public DirectionServiceImpl(DirectionRepository directionRepository, DirectionMapper directionMapper, UtilisateurRepository utilisateurRepository) {
        this.directionRepository = directionRepository;
        this.directionMapper = directionMapper;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public DirectionDTO create(DirectionDTO directionDTO) {
        log.info("Creation d'une direction : {}", directionDTO);
        return directionMapper.toDto(directionRepository.save(directionMapper.toEntity(directionDTO)));
    }

    @Override
    public DirectionDTO update(DirectionDTO directionDTO) {
        log.info("Update d'une direction : {}", directionDTO);
        return directionMapper.toDto(directionRepository.save(directionMapper.toEntity(directionDTO)));
    }

    @Override
    public Optional<DirectionDTO> getById(Long id) {
        log.info("Consultation d'une direction : {}", id);
        return Optional.ofNullable(directionMapper.toDto(directionRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<DirectionDTO> findAll(Pageable pageable) {
        log.info("Pages des directions");
        return directionRepository.findAll(pageable).map(directionMapper::toDto);
    }

    @Override
    public List<DirectionDTO> findAll() {
        log.info("Liste des directions");
        return directionRepository.findAll().stream().map(directionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'une direction : {}", id);
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        if (utilisateurs.isEmpty() || utilisateurs == null) {
            directionRepository.deleteById(id);
        } else {
            throw new CustomException("Impossible de supprimer cette direction regorge toujours d'agents.");
        }

    }

}
