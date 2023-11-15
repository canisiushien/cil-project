/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.domain.Exercice;
import cil.bf.activiteApp.domain.Programme;
import cil.bf.activiteApp.exception.CustomException;
import cil.bf.activiteApp.repository.ExerciceRepository;
import cil.bf.activiteApp.repository.ProgrammeRepository;
import cil.bf.activiteApp.service.ProgrammeService;
import cil.bf.activiteApp.service.dto.ProgrammeDTO;
import cil.bf.activiteApp.service.mapper.ProgrammeMapper;

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
public class ProgrammeServiceImpl implements ProgrammeService {

    private final ProgrammeRepository programmeRepository;

    private final ExerciceRepository exerciceRepository;

    private final ProgrammeMapper programmeMapper;

    public ProgrammeServiceImpl(ProgrammeRepository programmeRepository, ExerciceRepository exerciceRepository, ProgrammeMapper programmeMapper) {
        this.programmeRepository = programmeRepository;
        this.exerciceRepository = exerciceRepository;
        this.programmeMapper = programmeMapper;
    }

    @Override
    public ProgrammeDTO create(ProgrammeDTO p) {
        log.info("Creation d'un programme : {}", p);
        Exercice exercice = exerciceRepository.findById(p.getExercice().getId()).orElseThrow(() -> new CustomException("Cet exercice est inexistant."));
        Programme programme = programmeMapper.toEntity(p);
        programme.setExercice(exercice);
        return programmeMapper.toDto(programmeRepository.save(programme));
    }

    @Override
    public ProgrammeDTO update(ProgrammeDTO p) {
        log.info("Update d'un programme : {}", p);
        return programmeMapper.toDto(programmeRepository.save(programmeMapper.toEntity(p)));
    }

    @Override
    public Optional<ProgrammeDTO> getById(Long id) {
        log.info("Consultation d'un programme : {}", id);
        return Optional.ofNullable(programmeMapper.toDto(programmeRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<ProgrammeDTO> findAll(Pageable pageable) {
        log.info("Liste des programmes");
        return programmeRepository.findAll(pageable).map(programmeMapper::toDto);
    }

    @Override
    public List<ProgrammeDTO> findAll() {
        return programmeRepository.findAll().stream().map(programmeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'un programme : {}", id);
        programmeRepository.deleteById(id);
    }

}
