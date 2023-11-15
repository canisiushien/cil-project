/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.repository.InterimRepository;
import cil.bf.activiteApp.service.InterimService;
import cil.bf.activiteApp.service.dto.InterimDTO;
import cil.bf.activiteApp.service.mapper.InterimMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class InterimServiceImpl implements InterimService {

    private final InterimRepository interimRepository;

    private final InterimMapper interimMapper;

    public InterimServiceImpl(InterimRepository interimRepository, InterimMapper interimMapper) {
        this.interimRepository = interimRepository;
        this.interimMapper = interimMapper;
    }

    @Override
    public InterimDTO create(InterimDTO interimDTO) {
        log.info("Creation d'un interim : {}", interimDTO);
        return interimMapper.toDto(interimRepository.save(interimMapper.toEntity(interimDTO)));
    }

    @Override
    public InterimDTO update(InterimDTO interimDTO) {
        log.info("Update d'un interim : {}", interimDTO);
        return interimMapper.toDto(interimRepository.save(interimMapper.toEntity(interimDTO)));
    }

    @Override
    public Optional<InterimDTO> getById(Long id) {
        log.info("Creation d'une unité : {}", id);
        return Optional.ofNullable(interimMapper.toDto(interimRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<InterimDTO> findAll(Pageable pageable) {
        log.info("Liste des interims");
        return interimRepository.findAll(pageable).map(interimMapper::toDto);
    }


    @Override
    public List<InterimDTO> findAll() {
        return interimRepository.findAll().stream().map(interimMapper::toDto).collect(Collectors.toList());
    }


    @Override
    public void delete(Long id) {
        log.info("Suppression d'un interim : {}", id);
        interimRepository.deleteById(id);
    }

}
