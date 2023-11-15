/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.repository.TypeReunionRepository;
import cil.bf.activiteApp.service.TypeReunionService;
import cil.bf.activiteApp.service.dto.TypeReunionDTO;
import cil.bf.activiteApp.service.mapper.TypeReunionMapper;

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
public class TypeReunionServiceImpl implements TypeReunionService {

    private final TypeReunionRepository typeReunionRepository;

    private final TypeReunionMapper typeReunionMapper;

    public TypeReunionServiceImpl(TypeReunionRepository typeReunionRepository, TypeReunionMapper typeReunionMapper) {
        this.typeReunionRepository = typeReunionRepository;
        this.typeReunionMapper = typeReunionMapper;
    }

    @Override
    public TypeReunionDTO create(TypeReunionDTO t) {
        log.info("Creation d'un type de réunion : {}", t);
        return typeReunionMapper.toDto(typeReunionRepository.save(typeReunionMapper.toEntity(t)));
    }

    @Override
    public TypeReunionDTO update(TypeReunionDTO t) {
        log.info("Update d'un type de réunion : {}", t);
        return typeReunionMapper.toDto(typeReunionRepository.save(typeReunionMapper.toEntity(t)));
    }

    @Override
    public Optional<TypeReunionDTO> getById(Long id) {
        log.info("Consultation d'un type de réunion : {}", id);
        return Optional.ofNullable(typeReunionMapper.toDto(typeReunionRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<TypeReunionDTO> findAll(Pageable pageable) {
        log.info("Liste des types de réunion");
        return typeReunionRepository.findAll(pageable).map(typeReunionMapper::toDto);
    }

    @Override
    public List<TypeReunionDTO> findAll() {
        return typeReunionRepository.findAll().stream().map(typeReunionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'un type de réunion : {}", id);
        typeReunionRepository.deleteById(id);
    }

}
