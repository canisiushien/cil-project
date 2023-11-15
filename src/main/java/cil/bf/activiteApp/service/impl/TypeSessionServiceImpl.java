/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.repository.TypeSessionRepository;
import cil.bf.activiteApp.service.TypeSessionService;
import cil.bf.activiteApp.service.dto.TypeSessionDTO;
import cil.bf.activiteApp.service.mapper.TypeSessionMapper;

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
public class TypeSessionServiceImpl implements TypeSessionService {

    private final TypeSessionRepository typeSessionRepository;

    private final TypeSessionMapper typeSessionMapper;

    public TypeSessionServiceImpl(TypeSessionRepository typeSessionRepository, TypeSessionMapper typeSessionMapper) {
        this.typeSessionRepository = typeSessionRepository;
        this.typeSessionMapper = typeSessionMapper;
    }

    @Override
    public TypeSessionDTO create(TypeSessionDTO t) {
        log.info("Creation d'un type de session : {}", t);
        return typeSessionMapper.toDto(typeSessionRepository.save(typeSessionMapper.toEntity(t)));
    }

    @Override
    public TypeSessionDTO update(TypeSessionDTO t) {
        log.info("Update d'un type de session : {}", t);
        return typeSessionMapper.toDto(typeSessionRepository.save(typeSessionMapper.toEntity(t)));
    }

    @Override
    public Optional<TypeSessionDTO> getById(Long id) {
        log.info("Consultation d'un type de session : {}", id);
        return Optional.ofNullable(typeSessionMapper.toDto(typeSessionRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<TypeSessionDTO> findAll(Pageable pageable) {
        log.info("Liste des types de session");
        return typeSessionRepository.findAll(pageable).map(typeSessionMapper::toDto);
    }

    @Override
    public List<TypeSessionDTO> findAll() {
        return typeSessionRepository.findAll().stream().map(typeSessionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Suppresison d'un type de session : {}", id);
        typeSessionRepository.deleteById(id);
    }

}
