/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.domain.Document;
import cil.bf.activiteApp.domain.TypeDocument;
import cil.bf.activiteApp.domain.enums.StatutActivite;
import cil.bf.activiteApp.domain.Activite;
import cil.bf.activiteApp.domain.ObjectifOperationnel;
import cil.bf.activiteApp.exception.CustomException;
import cil.bf.activiteApp.repository.ActiviteRepository;
import cil.bf.activiteApp.repository.ObjectifOperationnelRepository;
import cil.bf.activiteApp.service.ActiviteService;
import cil.bf.activiteApp.service.DocumentService;
import cil.bf.activiteApp.service.dto.ActiviteDTO;
import cil.bf.activiteApp.service.dto.DocumentDTO;
import cil.bf.activiteApp.service.mapper.ActiviteMapper;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import cil.bf.activiteApp.service.mapper.DocumentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@Service
public class ActiviteServiceImpl implements ActiviteService {

    private final ActiviteRepository activiteRepository;

    private final ObjectifOperationnelRepository objectifOperationnelRepository;

    private final ActiviteMapper activiteMapper;

    private  final DocumentService documentService;

    private final DocumentMapper documentMapper;

    private final Path root = Paths.get("C:\\pieces");

    public ActiviteServiceImpl(ActiviteRepository activiteRepository, ObjectifOperationnelRepository objectifOperationnelRepository, ActiviteMapper activiteMapper, DocumentService documentService, DocumentMapper documentMapper) {
        this.activiteRepository = activiteRepository;
        this.objectifOperationnelRepository = objectifOperationnelRepository;
        this.activiteMapper = activiteMapper;
        this.documentService = documentService;
        this.documentMapper = documentMapper;
    }

    @Override
    public ActiviteDTO create(ActiviteDTO activiteDTO) {
        log.info("Creation d'une activité : {}", activiteDTO);
        ObjectifOperationnel objectifOperationnel = objectifOperationnelRepository.findById(activiteDTO.getObjectifOperationnel().getId()).orElseThrow(() -> new CustomException("Cet objectif operationnel est inexistant."));
        Activite activite = activiteMapper.toEntity(activiteDTO);
        activite.setIdObjectifOperationnel(objectifOperationnel);
        return activiteMapper.toDto(activiteRepository.save(activite));
    }

    @Override
    public ActiviteDTO update(ActiviteDTO activiteDTO) {
        log.info("Update d'une activité : {}", activiteDTO);
        return activiteMapper.toDto(activiteRepository.save(activiteMapper.toEntity(activiteDTO)));
    }

    @Override
    public Optional<ActiviteDTO> getById(Long id) {
        log.info("Consultation d'une action : {}", id);
        return Optional.ofNullable(activiteMapper.toDto(activiteRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<ActiviteDTO> findAll(Pageable pageable) {
        log.info("Page des activites");
        return activiteRepository.findAll(pageable).map(activiteMapper::toDto);
    }

    @Override
    public List<ActiviteDTO> findAll() {
        return activiteRepository.findAll().stream().map(activiteMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'une activite : {}", id);
        activiteRepository.deleteById(id);
    }

    @Override
    public ActiviteDTO cloturer(Long id, MultipartFile file) {
        log.info("Cloture d'une activite : {}", id);

        Activite activite = activiteRepository.findById(id).orElseThrow(() -> new CustomException("Cette activité n'existe pas."));

        DocumentDTO doc = new DocumentDTO();
        String basePath =root + "CIL/";
        String filename = documentService.generateFileName(file);
        doc.setChemin(basePath+filename);
        doc.setLibelle(filename);
        String extension=documentService.getFileExtension(file);
        doc.setFormat(extension);

        documentService.storeFile(file, basePath, filename);
        documentService.create(doc);

        activite.getDocuments().add(documentMapper.toEntity(doc));
        activite.setStatut(StatutActivite.TERMINEE);

        return activiteMapper.toDto(activiteRepository.save(activite));
    }
}
