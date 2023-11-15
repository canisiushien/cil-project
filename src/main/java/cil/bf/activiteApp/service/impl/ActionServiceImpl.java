/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.domain.Action;
import cil.bf.activiteApp.domain.ObjectifStrategique;
import cil.bf.activiteApp.exception.CustomException;
import cil.bf.activiteApp.repository.ActionRepository;
import cil.bf.activiteApp.repository.ObjectifStrategiqueRepository;
import cil.bf.activiteApp.service.ActionService;
import cil.bf.activiteApp.service.dto.ActionDTO;
import cil.bf.activiteApp.service.dto.ExerciceDTO;
import cil.bf.activiteApp.service.mapper.ActionMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@lombok.extern.slf4j.Slf4j
@Service
public class ActionServiceImpl implements ActionService {

    private final ActionRepository actionRepository;

    private final ObjectifStrategiqueRepository objectifStrategiqueRepository;

    private final ActionMapper actionMapper;

    public ActionServiceImpl(ActionRepository actionRepository, ObjectifStrategiqueRepository objectifStrategiqueRepository, ActionMapper actionMapper) {
        this.actionRepository = actionRepository;
        this.objectifStrategiqueRepository = objectifStrategiqueRepository;
        this.actionMapper = actionMapper;
    }

    @Override
    public ActionDTO create(ActionDTO actionDTO) {
        log.info("Creation d'une action : {}", actionDTO);
        ObjectifStrategique objectifStrategique = objectifStrategiqueRepository.findById(actionDTO.getObjectifStrategique().getId()).orElseThrow(() -> new CustomException("Cet objectif stratégique est inexistant."));
        Action action = actionMapper.toEntity(actionDTO);
        action.setObjectifStrategique(objectifStrategique);
        return actionMapper.toDto(actionRepository.save(action));
    }

    @Override
    public ActionDTO update(ActionDTO actionDTO) {
        log.info("Update d'une action : {}", actionDTO);
        return actionMapper.toDto(actionRepository.save(actionMapper.toEntity(actionDTO)));
    }

    @Override
    public Optional<ActionDTO> getById(Long id) {
        log.info("Consultation d'une action : {}", id);
        return Optional.ofNullable(actionMapper.toDto(actionRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<ActionDTO> findAll(Pageable pageable) {
        log.info("Pages des actions");
        return actionRepository.findAll(pageable).map(actionMapper::toDto);
    }

    @Override
    public List<ActionDTO> findAll() {
        return actionRepository.findAll().stream().map(actionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'une actions : {}", id);
        actionRepository.deleteById(id);
    }

}
