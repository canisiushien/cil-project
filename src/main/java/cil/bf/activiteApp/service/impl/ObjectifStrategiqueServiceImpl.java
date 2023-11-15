/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.domain.ObjectifStrategique;
import cil.bf.activiteApp.domain.Programme;
import cil.bf.activiteApp.exception.CustomException;
import cil.bf.activiteApp.repository.ObjectifStrategiqueRepository;
import cil.bf.activiteApp.repository.ProgrammeRepository;
import cil.bf.activiteApp.service.ObjectifStrategiqueService;
import cil.bf.activiteApp.service.dto.ObjectifStrategiqueDTO;
import cil.bf.activiteApp.service.mapper.ObjectifStrategiqueMapper;

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
public class ObjectifStrategiqueServiceImpl implements ObjectifStrategiqueService {

    private final ObjectifStrategiqueRepository objectifStrategiqueRepository;

    private final ProgrammeRepository programmeRepository;

    private final ObjectifStrategiqueMapper objectifStrategiqueMapper;

    public ObjectifStrategiqueServiceImpl(ObjectifStrategiqueRepository objectifStrategiqueRepository, ProgrammeRepository programmeRepository, ObjectifStrategiqueMapper objectifStrategiqueMapper) {
        this.objectifStrategiqueRepository = objectifStrategiqueRepository;
        this.programmeRepository = programmeRepository;
        this.objectifStrategiqueMapper = objectifStrategiqueMapper;
    }

    @Override
    public ObjectifStrategiqueDTO create(ObjectifStrategiqueDTO o) {
        log.info("Creation d'un objectif strategique : {}", o);
        Programme programme = programmeRepository.findById(o.getProgramme().getId()).orElseThrow(() -> new CustomException("Cet programme est inexistant."));
        ObjectifStrategique objectifStrategique = objectifStrategiqueMapper.toEntity(o);
        objectifStrategique.setProgramme(programme);
        return objectifStrategiqueMapper.toDto(objectifStrategiqueRepository.save(objectifStrategique));
    }

    @Override
    public ObjectifStrategiqueDTO update(ObjectifStrategiqueDTO o) {
        log.info("Update d'un objectif strategique : {}", o);
        return objectifStrategiqueMapper.toDto(objectifStrategiqueRepository.save(objectifStrategiqueMapper.toEntity(o)));
    }

    @Override
    public Optional<ObjectifStrategiqueDTO> getById(Long id) {
        log.info("Consultation d'un objectif strategique : {}", id);
        return Optional.ofNullable(objectifStrategiqueMapper.toDto(objectifStrategiqueRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<ObjectifStrategiqueDTO> findAll(Pageable pageable) {
        log.info("Liste des objectifs strategiques");
        return objectifStrategiqueRepository.findAll(pageable).map(objectifStrategiqueMapper::toDto);
    }

    @Override
    public List<ObjectifStrategiqueDTO> findAll() {
        return objectifStrategiqueRepository.findAll().stream().map(objectifStrategiqueMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'un objectif strategique : {}", id);
        objectifStrategiqueRepository.deleteById(id);
    }

}
