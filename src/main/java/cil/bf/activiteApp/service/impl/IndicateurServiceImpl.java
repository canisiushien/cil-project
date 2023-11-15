/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.repository.IndicateurRepository;
import cil.bf.activiteApp.service.IndicateurService;
import cil.bf.activiteApp.service.dto.IndicateurDTO;
import cil.bf.activiteApp.service.mapper.IndicateurMapper;
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
public class IndicateurServiceImpl implements IndicateurService {

    private final IndicateurRepository indicateurRepository;

    private final IndicateurMapper indicateurMapper;

    public IndicateurServiceImpl(IndicateurRepository indicateurRepository, IndicateurMapper indicateurMapper) {
        this.indicateurRepository = indicateurRepository;
        this.indicateurMapper = indicateurMapper;
    }

    @Override
    public IndicateurDTO create(IndicateurDTO indicateurDTO) {
        log.info("Creation d'un indicateur : {}", indicateurDTO);
        return indicateurMapper.toDto(indicateurRepository.save(indicateurMapper.toEntity(indicateurDTO)));
    }

    @Override
    public IndicateurDTO update(IndicateurDTO indicateurDTO) {
        log.info("Update d'un indicateur : {}", indicateurDTO);
        return indicateurMapper.toDto(indicateurRepository.save(indicateurMapper.toEntity(indicateurDTO)));
    }

    @Override
    public Optional<IndicateurDTO> getById(Long id) {
        log.info("Consultation d'un indicateur : {}", id);
        return Optional.ofNullable(indicateurMapper.toDto(indicateurRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<IndicateurDTO> findAll(Pageable pageable) {
        log.info("Liste des indicateurs");
        return indicateurRepository.findAll(pageable).map(indicateurMapper::toDto);
    }

    @Override
    public List<IndicateurDTO> findAll() {
        return indicateurRepository.findAll().stream().map(indicateurMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'un indicateur : {}", id);
        indicateurRepository.deleteById(id);
    }

}
