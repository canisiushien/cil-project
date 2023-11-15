/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.domain.Document;
import cil.bf.activiteApp.domain.TypeDocument;
import cil.bf.activiteApp.exception.CustomException;
import cil.bf.activiteApp.repository.DocumentRepository;
import cil.bf.activiteApp.repository.TypeDocumentRepository;
import cil.bf.activiteApp.service.DocumentService;
import cil.bf.activiteApp.service.dto.DocumentDTO;
import cil.bf.activiteApp.service.mapper.DocumentMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {
    private final Path root = Paths.get("C:\\pieces");

    private final DocumentRepository documentRepository;

    private final TypeDocumentRepository typeDocumentRepository;

    private final DocumentMapper documentMapper;

    public DocumentServiceImpl(DocumentRepository documentRepository, DocumentMapper documentMapper, TypeDocumentRepository typeDocumentRepository) {
        this.documentRepository = documentRepository;
        this.typeDocumentRepository = typeDocumentRepository;
        this.documentMapper = documentMapper;
    }



    @PostConstruct
    public void initFolderCreate() {
        String cil = root + "CIL/";
        if (!existFolder(cil)) {
            createFolder(cil);

        }
    }



    @Override
    public DocumentDTO create(DocumentDTO documentDTO) {
        log.info("Creation d'un document : {}", documentDTO);
        TypeDocument typeDocument = typeDocumentRepository.findById(documentDTO.getTypeDocument().getId()).orElseThrow(() -> new CustomException("Cet type de document est inexistant."));
        Document document = documentMapper.toEntity(documentDTO);
        document.setTypeDocument(typeDocument);
        return documentMapper.toDto(documentRepository.save(document));
    }

    @Override
    public DocumentDTO update(DocumentDTO documentDTO) {
        log.info("Update d'un document : {}", documentDTO);
        return documentMapper.toDto(documentRepository.save(documentMapper.toEntity(documentDTO)));
    }

    @Override
    public Optional<DocumentDTO> getById(Long id) {
        log.info("Consultation d'un document : {}", id);
        return Optional.ofNullable(documentMapper.toDto(documentRepository.findById(id).orElse(null)));
    }

    @Override
    public Page<DocumentDTO> findAll(Pageable pageable) {
        log.info("Pages des documents");
        return documentRepository.findAll(pageable).map(documentMapper::toDto);
    }

    @Override
    public List<DocumentDTO> findAll() {
        return documentRepository.findAll().stream().map(documentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        log.info("Suppression d'un document : {}", id);
        documentRepository.deleteById(id);
    }

    /**
     * TELECHARGER UN DOCUMENT
     *
     * @param fileUri : chemin du fichier
     * @return
     */
    @Override
    public Resource download(String fileUri) {
        log.info("Telechargement/Lecture d'un Document : {}", fileUri);
        Document document = documentRepository.findByChemin(fileUri).orElseThrow(() -> new CustomException("Document [" + fileUri + "] introuvable !"));

        try {
            Resource resource = null;
            Path filePath = Paths.get(document.getChemin()).toAbsolutePath();
            resource = new UrlResource(filePath.toUri());//type Resource pour permettre la lecture du fichier

            if (!resource.exists() || !resource.isReadable()) {
                throw new CustomException("Aucun document disponible.");
            }

            return resource;
        } catch (MalformedURLException e) {
            log.error("Erreur de téléchargement : " + e.getMessage());
            throw new CustomException("Document indisponible. Veuillez réessayer plus tard SVP.");
        }
    }



    /**
     * CHARGER UN DOCUMENT
     *
     * @param multipartFile : fichier
     * @return
     */


    @Override
    public String storeFile(MultipartFile multipartFile, String folder, String filename) {
        log.debug("created file  : {}", filename);
        try {
            try (InputStream inputStream = multipartFile.getInputStream()) {
                Files.copy(inputStream, Paths.get(folder + filename), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return filename;
    }




    @Override
    public byte[] readFile(String libelle, String chemin) throws IOException {
        File file = new File(chemin + libelle);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

    @Override
    public String generateFileName(MultipartFile multipartFile) {
        String filename = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        return new Date().getTime() + "-" + filename.replace(" ", "_");
    }

    @Override
    public String getFileExtension(MultipartFile file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

    private boolean existFolder(String folder) {
        log.debug("check if folder  : {} exist", folder);
        File file = new File(folder);
        return file.exists();
    }

    private boolean createFolder(String folder) {
        log.debug("created file store root folder : {}", folder);
        return new File(folder).mkdirs();
    }





  

}
