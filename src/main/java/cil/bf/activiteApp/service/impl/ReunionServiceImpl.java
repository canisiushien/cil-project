/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.domain.Reunion;
import cil.bf.activiteApp.domain.TypeReunion;
import cil.bf.activiteApp.domain.TypeSession;
import cil.bf.activiteApp.domain.enums.EtatReunion;
import cil.bf.activiteApp.exception.CustomException;
import cil.bf.activiteApp.repository.ReunionRepository;
import cil.bf.activiteApp.repository.TypeReunionRepository;
import cil.bf.activiteApp.repository.TypeSessionRepository;
import cil.bf.activiteApp.repository.UtilisateurRepository;
import cil.bf.activiteApp.service.DocumentService;
import cil.bf.activiteApp.service.NumeroGeneretorService;
import cil.bf.activiteApp.service.ReunionService;
import cil.bf.activiteApp.service.constant.ConstantList;
import cil.bf.activiteApp.service.dto.DocumentDTO;
import cil.bf.activiteApp.service.dto.ReunionDTO;
import cil.bf.activiteApp.service.mapper.DocumentMapper;
import cil.bf.activiteApp.service.mapper.ReunionMapper;
import cil.bf.activiteApp.utils.CodeGenerator;
import jakarta.transaction.Transactional;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
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
public class ReunionServiceImpl implements ReunionService {

    private final ReunionRepository repository;
    private final TypeReunionRepository typeReunionRepository;
    private final TypeSessionRepository typeSessionRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ReunionMapper reunionMapper;
    private final DocumentService documentService;
    private final DocumentMapper documentMapper;
    private final Path root = Paths.get("C:\\pieces");

    private final NumeroGeneretorService numeroGeneretorService;

    public ReunionServiceImpl(
            ReunionRepository repository,
            ReunionMapper reunionMapper,
            DocumentService documentService,
            DocumentMapper documentMapper,
            NumeroGeneretorService numeroGeneretorService,
            TypeReunionRepository typeReunionRepository,
            TypeSessionRepository typeSessionRepository,
            UtilisateurRepository utilisateurRepository
    ) {
        this.repository = repository;
        this.reunionMapper = reunionMapper;
        this.documentService = documentService;
        this.documentMapper = documentMapper;
        this.numeroGeneretorService = numeroGeneretorService;
        this.typeReunionRepository = typeReunionRepository;
        this.typeSessionRepository = typeSessionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    @Transactional
    public ReunionDTO create(ReunionDTO r) {
        log.info("Creation d'une réunion : {}", r);
        ReunionDTO saved = null;

        try {

            TypeReunion type = typeReunionRepository.getReferenceById(r.getIdTypeReunion());
            TypeSession typeSession = typeSessionRepository.getReferenceById(r.getIdTypeSession());
            //Attribution des numeros en fonction du type
            if (null == r.getNumeroReunion()) {

                if (Objects.equals(type.getCode(), "RCAB")) {

                    //calcul des numero
                    Integer nextNumber = numeroGeneretorService
                            .getNextNumber(ConstantList.TypeNumero.RCAB);

                    String numero = CodeGenerator.generateNumeroCAB(String.valueOf(nextNumber));
                    r.setNumeroReunion(numero);
                    //  System.out.println("numeroReunion"+saved);

                }
                if (Objects.equals(type.getCode(), "RDIR")) {
                    Integer nextNumber = numeroGeneretorService
                            .getNextNumber(ConstantList.TypeNumero.RDIR);

                    String numero = CodeGenerator.generateNumeroCAB(String.valueOf(nextNumber));
                    r.setNumeroReunion(numero);
                }

                if (Objects.equals(type.getCode(), "RCODIR")) {
                    Integer nextNumber = numeroGeneretorService
                            .getNextNumber(ConstantList.TypeNumero.RCODIR);

                    String numero = CodeGenerator.generateNumeroCAB(String.valueOf(nextNumber));
                    r.setNumeroReunion(numero);
                }

                if (Objects.equals(type.getCode(), "RCF")) {
                    Integer nextNumber = numeroGeneretorService
                            .getNextNumber(ConstantList.TypeNumero.RCF);

                    String numero = CodeGenerator.generateNumeroCAB(String.valueOf(nextNumber));
                    r.setNumeroReunion(numero);
                }

                if (Objects.equals(typeSession.getCode(), "AG")) {
                    Integer nextNumber = numeroGeneretorService
                            .getNextNumber(ConstantList.TypeNumero.AG);

                    String numero = CodeGenerator.generateNumeroCAB(String.valueOf(nextNumber));
                    r.setNumeroReunion(numero);
                }
                if (Objects.equals(typeSession.getCode(), "SO")) {
                    Integer nextNumber = numeroGeneretorService
                            .getNextNumber(ConstantList.TypeNumero.SO);

                    String numero = CodeGenerator.generateNumeroCAB(String.valueOf(nextNumber));
                    r.setNumeroReunion(numero);
                }
                if (Objects.equals(typeSession.getCode(), "SE")) {
                    Integer nextNumber = numeroGeneretorService
                            .getNextNumber(ConstantList.TypeNumero.SE);
                    String numero = CodeGenerator.generateNumeroCAB(String.valueOf(nextNumber));
                    r.setNumeroReunion(numero);
                }
            }
            saved = reunionMapper.toDto(repository.save(reunionMapper.toEntity(r)));
        } catch (Exception ex) {
            throw new CustomException("ERROR REUNION " + ex.getMessage());
        }

        return saved;
    }

    @Override
    public ReunionDTO update(ReunionDTO r) {
        log.info("Update d'une réunion : {}", r);
        return reunionMapper.toDto(repository.save(reunionMapper.toEntity(r)));
    }

    @Override
    public Optional<ReunionDTO> getById(Long id) {
        log.info("Consultation d'une réunion : {}", id);
        return Optional.ofNullable(reunionMapper.toDto(repository.findById(id).orElse(null)));
    }

    @Override
    @Transactional
    public Page<ReunionDTO> findAll(Pageable pageable) {
        log.info("Liste des réunions");
        return repository.findAll(pageable).map(reunionMapper::toDto);
    }

    @Override
    public List<ReunionDTO> findAll() {
        return repository.findAll().stream().map(reunionMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'une réunion : {}", id);
        repository.deleteById(id);
    }

    @Override
    public List<ReunionDTO> findByTypeReunion(Long id) {

        return repository.findByTypeReunionId(id).stream().map(
                reunionMapper::toDto
        ).collect(Collectors.toList());

    }

    @Override
    public ReunionDTO reprogrammer(ReunionDTO reunionDTO) {
        ReunionDTO r = getById(reunionDTO.getId()).get();

        if (r.getEtat().equals(EtatReunion.ANNULE) || r.getEtat().equals(EtatReunion.REPROGRAMME)) {
            reunionDTO.setEtat(EtatReunion.INIITIE);
        } else {
            throw new CustomException("Cette reunion n'est ni ANNULEE ni A REPROGRAMME");
        }
        return reunionMapper.toDto(repository.save(reunionMapper.toEntity(reunionDTO)));
    }

    public ReunionDTO validerReunion(ReunionDTO request) {
        if (request.getId() == null) {
            throw new CustomException("Identifiant null");
        }

        ReunionDTO reunion = getById(request.getId()).get();
        reunion.setEtat(EtatReunion.VALIDE);

        reunion = reunionMapper.toDto(repository.save(reunionMapper.toEntity(reunion)));
        reunion.setEmailsParticipants(this.constructMailDestinataires(reunion));

        return reunion;
    }

    private List<String> constructMailDestinataires(ReunionDTO reunionDTO) {
        List<String> emails = new ArrayList<>();
        emails.add(utilisateurRepository.findById(reunionDTO.getPresident()).get().getEmail());
        emails.add(utilisateurRepository.findById(reunionDTO.getRapporteur()).get().getEmail());
        for (Long id : reunionDTO.getMembresReunion()) {
            emails.add(utilisateurRepository.findById(id).get().getEmail());
        }

        return emails;
    }

    @Override
    public ReunionDTO annulerReunion(Long id, String statut) {
        if (id == null) {
            throw new CustomException("Identifiant null");
        }

        ReunionDTO reunion = getById(id).get();
        if (reunion.getEtat().equals(EtatReunion.VALIDE)) {
            EtatReunion etatReunion = EtatReunion.ANNULE;
            if (statut.equalsIgnoreCase(etatReunion.getLabel())) {
                reunion.setEtat(etatReunion);
            } else {
                throw new CustomException("Echec du changement de la valeur du statut");
            }
        } else {
            throw new CustomException("La valeur recuperer pour le statut est differend de valide");
        }

        return reunionMapper.toDto(repository.save(reunionMapper.toEntity(reunion)));
    }

    @Override
    public ReunionDTO cloturer(Long id, MultipartFile file) {
        log.info("Cloture d'une reunion : {}", id);

        Reunion reunion = repository.findById(id).orElseThrow(() -> new CustomException("Cette reunion n'existe pas."));

        DocumentDTO doc = new DocumentDTO();
        String basePath = root + "CIL/";
        String filename = documentService.generateFileName(file);
        doc.setChemin(basePath + filename);
        doc.setLibelle(filename);
        String extension = documentService.getFileExtension(file);
        doc.setFormat(extension);

        documentService.storeFile(file, basePath, filename);
        documentService.create(doc);

        reunion.getDocuments().add(documentMapper.toEntity(doc));
        reunion.setEtat(EtatReunion.CLOTURE);

        return reunionMapper.toDto(repository.save(reunion));
    }
}
