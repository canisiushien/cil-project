/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.repository.MandatControleRepository;
import cil.bf.activiteApp.service.MandatControleService;
import cil.bf.activiteApp.service.dto.MandatControleDTO;
import cil.bf.activiteApp.service.mapper.MandatControleMapper;

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
public class MandatControleServiceImpl implements MandatControleService {

    private final MandatControleRepository mandatControleRepository;

    private final MandatControleMapper mandatControleMapper;

    public MandatControleServiceImpl(MandatControleRepository mandatControleRepository, MandatControleMapper mandatControleMapper) {
        this.mandatControleRepository = mandatControleRepository;
        this.mandatControleMapper = mandatControleMapper;
    }

    @Override
    public MandatControleDTO create(MandatControleDTO mandatControleDTO) {
        log.info("Creation d'un mandat : {}", mandatControleDTO);
        return mandatControleMapper.toDto(mandatControleRepository.save(mandatControleMapper.toEntity(mandatControleDTO)));
    }

    @Override
    public MandatControleDTO update(MandatControleDTO mandatControleDTO) {
        log.info("Update d'un mandat : {}", mandatControleDTO);
        return mandatControleMapper.toDto(mandatControleRepository.save(mandatControleMapper.toEntity(mandatControleDTO)));
    }

    @Override
    public Optional<MandatControleDTO> getById(Long id) {
        log.info("Consultation d'un mandat : {}", id);
        return Optional.ofNullable(mandatControleMapper.toDto(mandatControleRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<MandatControleDTO> findAll(Pageable pageable) {
        log.info("Liste des mandats");
        return mandatControleRepository.findAll(pageable).map(mandatControleMapper::toDto);
    }

    @Override
    public List<MandatControleDTO> findAll() {
        return mandatControleRepository.findAll().stream().map(mandatControleMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("suppression d'un mandat : {}", id);
        mandatControleRepository.deleteById(id);
    }

}
