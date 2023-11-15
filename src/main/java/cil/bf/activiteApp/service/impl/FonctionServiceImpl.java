/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.repository.FonctionRepository;
import cil.bf.activiteApp.service.FonctionService;
import cil.bf.activiteApp.service.dto.FonctionDTO;
import cil.bf.activiteApp.service.mapper.FonctionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
/**
 *
 * KABORE
 */
@Slf4j
@Service
public class FonctionServiceImpl implements FonctionService {

    private final FonctionRepository fonctionRepository;

    private final FonctionMapper fonctionMapper;

    public FonctionServiceImpl(FonctionRepository fonctionRepository, FonctionMapper fonctionMapper) {
        this.fonctionRepository = fonctionRepository;
        this.fonctionMapper = fonctionMapper;
    }

    @Override
    public FonctionDTO create(FonctionDTO fonctionDTO) {
        return fonctionMapper.toDto(fonctionRepository.save(fonctionMapper.toEntity(fonctionDTO)));
    }

    @Override
    public FonctionDTO update(FonctionDTO fonctionDTO) {
        return fonctionMapper.toDto(fonctionRepository.save(fonctionMapper.toEntity(fonctionDTO)));
    }

    @Override
    public Optional<FonctionDTO> getById(Long id) {
        return Optional.ofNullable(fonctionMapper.toDto(fonctionRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<FonctionDTO> findAll(Pageable pageable) {
        return fonctionRepository.findAll(pageable).map(fonctionMapper::toDto);
    }
    @Override
    public List<FonctionDTO> findAll() {
        log.info("Liste des fonctions");
        return fonctionRepository.findAll().stream().map(fonctionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        fonctionRepository.deleteById(id);
    }

}
