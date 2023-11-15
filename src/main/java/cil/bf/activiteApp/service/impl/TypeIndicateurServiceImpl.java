/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.repository.TypeIndicateurRepository;
import cil.bf.activiteApp.service.TypeIndicateurService;
import cil.bf.activiteApp.service.dto.TypeIndicateurDTO;
import cil.bf.activiteApp.service.mapper.TypeIndicateurMapper;

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
public class TypeIndicateurServiceImpl implements TypeIndicateurService {

    private final TypeIndicateurRepository typeIndicateurRepository;

    private final TypeIndicateurMapper typeIndicateurMapper;

    public TypeIndicateurServiceImpl(TypeIndicateurRepository typeIndicateurRepository, TypeIndicateurMapper typeIndicateurMapper) {
        this.typeIndicateurRepository = typeIndicateurRepository;
        this.typeIndicateurMapper = typeIndicateurMapper;
    }

    @Override
    public TypeIndicateurDTO create(TypeIndicateurDTO tO) {
        log.info("Creation d'un type d'indication : {}", tO);
        return typeIndicateurMapper.toDto(typeIndicateurRepository.save(typeIndicateurMapper.toEntity(tO)));
    }

    @Override
    public TypeIndicateurDTO update(TypeIndicateurDTO tO) {
        log.info("Update d'un type d'indicateur : {}", tO);
        return typeIndicateurMapper.toDto(typeIndicateurRepository.save(typeIndicateurMapper.toEntity(tO)));
    }

    @Override
    public Optional<TypeIndicateurDTO> getById(Long id) {
        log.info("Consultation d'un type d'indicateur : {}", id);
        return Optional.ofNullable(typeIndicateurMapper.toDto(typeIndicateurRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<TypeIndicateurDTO> findAll(Pageable pageable) {
        log.info("Liste des types d'indicateurs");
        return typeIndicateurRepository.findAll(pageable).map(typeIndicateurMapper::toDto);
    }

    @Override
    public List<TypeIndicateurDTO> findAll() {
        return typeIndicateurRepository.findAll().stream().map(typeIndicateurMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'un type d'indicateur : {}", id);
        typeIndicateurRepository.deleteById(id);
    }

}
