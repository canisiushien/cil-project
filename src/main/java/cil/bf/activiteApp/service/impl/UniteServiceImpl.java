/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.repository.UniteRepository;
import cil.bf.activiteApp.service.UniteService;
import cil.bf.activiteApp.service.dto.UniteDTO;
import cil.bf.activiteApp.service.mapper.UniteMapper;

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
public class UniteServiceImpl implements UniteService {

    private final UniteRepository uniteRepository;

    private final UniteMapper uniteMapper;

    public UniteServiceImpl(UniteRepository uniteRepository, UniteMapper uniteMapper) {
        this.uniteRepository = uniteRepository;
        this.uniteMapper = uniteMapper;
    }

    @Override
    public UniteDTO create(UniteDTO u) {
        log.info("Creation d'une unité : {}", u);
        return uniteMapper.toDto(uniteRepository.save(uniteMapper.toEntity(u)));
    }

    @Override
    public UniteDTO update(UniteDTO u) {
        log.info("Update d'une unité : {}", u);
        return uniteMapper.toDto(uniteRepository.save(uniteMapper.toEntity(u)));
    }

    @Override
    public Optional<UniteDTO> getById(Long id) {
        log.info("Consultation d'une unité : {}", id);
        return Optional.ofNullable(uniteMapper.toDto(uniteRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<UniteDTO> findAll(Pageable pageable) {
        log.info("Liste des unités");
        return uniteRepository.findAll(pageable).map(uniteMapper::toDto);
    }

    @Override
    public List<UniteDTO> findAll() {
        return uniteRepository.findAll().stream().map(uniteMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'une unité : {}", id);
        uniteRepository.deleteById(id);
    }

}
