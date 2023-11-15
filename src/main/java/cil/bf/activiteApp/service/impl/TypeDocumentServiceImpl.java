/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.repository.TypeDocumentRepository;
import cil.bf.activiteApp.service.TypeDocumentService;
import cil.bf.activiteApp.service.dto.TypeDocumentDTO;
import cil.bf.activiteApp.service.mapper.TypeDocumentMapper;

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
public class TypeDocumentServiceImpl implements TypeDocumentService {

    private final TypeDocumentRepository typeDocumentRepository;

    private final TypeDocumentMapper typeDocumentMapper;

    public TypeDocumentServiceImpl(TypeDocumentRepository typeDocumentRepository, TypeDocumentMapper typeDocumentMapper) {
        this.typeDocumentRepository = typeDocumentRepository;
        this.typeDocumentMapper = typeDocumentMapper;
    }

    @Override
    public TypeDocumentDTO create(TypeDocumentDTO tO) {
        log.info("Creation d'un type de document : {}", tO);
        return typeDocumentMapper.toDto(typeDocumentRepository.save(typeDocumentMapper.toEntity(tO)));
    }

    @Override
    public TypeDocumentDTO update(TypeDocumentDTO tO) {
        log.info("Update d'un type de document : {}", tO);
        return typeDocumentMapper.toDto(typeDocumentRepository.save(typeDocumentMapper.toEntity(tO)));
    }

    @Override
    public Optional<TypeDocumentDTO> getById(Long id) {
        log.info("Consultation d'un type de document : {}", id);
        return Optional.ofNullable(typeDocumentMapper.toDto(typeDocumentRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<TypeDocumentDTO> findAll(Pageable pageable) {
        log.info("Liste des types de documents");
        return typeDocumentRepository.findAll(pageable).map(typeDocumentMapper::toDto);
    }

    @Override
    public List<TypeDocumentDTO> findAll() {
        return typeDocumentRepository.findAll().stream().map(typeDocumentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'un type de document : {}", id);
        typeDocumentRepository.deleteById(id);
    }

}
