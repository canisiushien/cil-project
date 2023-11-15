/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.repository.MissionRepository;
import cil.bf.activiteApp.service.MissionService;
import cil.bf.activiteApp.service.dto.MissionDTO;
import cil.bf.activiteApp.service.mapper.MissionMapper;

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
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;

    private final MissionMapper missionMapper;

    public MissionServiceImpl(MissionRepository missionRepository, MissionMapper missionMapper) {
        this.missionRepository = missionRepository;
        this.missionMapper = missionMapper;
    }

    @Override
    public MissionDTO create(MissionDTO missionDTO) {
        log.info("Creation d'une mission : {}", missionDTO);
        return missionMapper.toDto(missionRepository.save(missionMapper.toEntity(missionDTO)));
    }

    @Override
    public MissionDTO update(MissionDTO missionDTO) {
        log.info("Update d'une mission : {}", missionDTO);
        return missionMapper.toDto(missionRepository.save(missionMapper.toEntity(missionDTO)));
    }

    @Override
    public Optional<MissionDTO> getById(Long id) {
        log.info("Consultation d'une mission : {}", id);
        return Optional.ofNullable(missionMapper.toDto(missionRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<MissionDTO> findAll(Pageable pageable) {
        log.info("Liste des missions");
        return missionRepository.findAll(pageable).map(missionMapper::toDto);
    }

    @Override
    public List<MissionDTO> findAll() {
        return missionRepository.findAll().stream().map(missionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'une mission : {}", id);
        missionRepository.deleteById(id);
    }

}
