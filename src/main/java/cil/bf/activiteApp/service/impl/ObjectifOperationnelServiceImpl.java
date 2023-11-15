/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.domain.Action;
import cil.bf.activiteApp.domain.ObjectifOperationnel;
import cil.bf.activiteApp.exception.CustomException;
import cil.bf.activiteApp.repository.ActionRepository;
import cil.bf.activiteApp.repository.ObjectifOperationnelRepository;
import cil.bf.activiteApp.service.ObjectifOperationnelService;
import cil.bf.activiteApp.service.dto.ObjectifOperationnelDTO;
import cil.bf.activiteApp.service.mapper.ObjectifOperationnelMapper;

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
public class ObjectifOperationnelServiceImpl implements ObjectifOperationnelService {

    private final ObjectifOperationnelRepository objectifOperationnelRepository;

    private final ActionRepository actionRepository;

    private final ObjectifOperationnelMapper objectifOperationnelMapper;

    public ObjectifOperationnelServiceImpl(ObjectifOperationnelRepository objectifOperationnelRepository, ActionRepository actionRepository, ObjectifOperationnelMapper objectifOperationnelMapper) {
        this.objectifOperationnelRepository = objectifOperationnelRepository;
        this.actionRepository = actionRepository;
        this.objectifOperationnelMapper = objectifOperationnelMapper;
    }

    @Override
    public ObjectifOperationnelDTO create(ObjectifOperationnelDTO o) {
        log.info("Creation d'un objectif operationnel : {}", o);
        Action action = actionRepository.findById(o.getAction().getId()).orElseThrow(() -> new CustomException("Cette action est inexistant."));
        ObjectifOperationnel objectifOperationnel = objectifOperationnelMapper.toEntity(o);
        objectifOperationnel.setAction(action);
        return objectifOperationnelMapper.toDto(objectifOperationnelRepository.save(objectifOperationnel));
    }

    @Override
    public ObjectifOperationnelDTO update(ObjectifOperationnelDTO o) {
        log.info("Update d'un objectif operationnel : {}", o);
        return objectifOperationnelMapper.toDto(objectifOperationnelRepository.save(objectifOperationnelMapper.toEntity(o)));
    }

    @Override
    public Optional<ObjectifOperationnelDTO> getById(Long id) {
        log.info("consultation d'un objectif operationnel : {}", id);
        return Optional.ofNullable(objectifOperationnelMapper.toDto(objectifOperationnelRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<ObjectifOperationnelDTO> findAll(Pageable pageable) {
        log.info("Liste des objectifs operationnels");
        return objectifOperationnelRepository.findAll(pageable).map(objectifOperationnelMapper::toDto);
    }

    @Override
    public List<ObjectifOperationnelDTO> findAll() {
        return objectifOperationnelRepository.findAll().stream().map(objectifOperationnelMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'un objectif operationnel : {}", id);
        objectifOperationnelRepository.deleteById(id);
    }
}
