/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.repository.PeriodiciteRepository;
import cil.bf.activiteApp.service.PeriodiciteService;
import cil.bf.activiteApp.service.dto.PeriodiciteDTO;
import cil.bf.activiteApp.service.mapper.PeriodiciteMapper;

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
public class PeriodiciteServiceImpl implements PeriodiciteService {

    private final PeriodiciteRepository periodiciteRepository;

    private final PeriodiciteMapper periodiciteMapper;

    public PeriodiciteServiceImpl(PeriodiciteRepository periodiciteRepository, PeriodiciteMapper periodiciteMapper) {
        this.periodiciteRepository = periodiciteRepository;
        this.periodiciteMapper = periodiciteMapper;
    }

    @Override
    public PeriodiciteDTO create(PeriodiciteDTO pdto) {
        log.info("Creation d'une périodicité : {}", pdto);
        return periodiciteMapper.toDto(periodiciteRepository.save(periodiciteMapper.toEntity(pdto)));
    }

    @Override
    public PeriodiciteDTO update(PeriodiciteDTO pdto) {
        log.info("Update d'une périodicité : {}", pdto);
        return periodiciteMapper.toDto(periodiciteRepository.save(periodiciteMapper.toEntity(pdto)));
    }

    @Override
    public Optional<PeriodiciteDTO> getById(Long id) {
        log.info("Consultation d'une périodicité : {}", id);
        return Optional.ofNullable(periodiciteMapper.toDto(periodiciteRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<PeriodiciteDTO> findAll(Pageable pageable) {
        log.info("Liste des périodicités");
        return periodiciteRepository.findAll(pageable).map(periodiciteMapper::toDto);
    }

    @Override
    public List<PeriodiciteDTO> findAll() {
        return periodiciteRepository.findAll().stream().map(periodiciteMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'une périodicité : {}", id);
        periodiciteRepository.deleteById(id);
    }

}
