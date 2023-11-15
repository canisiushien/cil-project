/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service;

import cil.bf.activiteApp.service.dto.DocumentDTO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface DocumentService {

    DocumentDTO create(DocumentDTO documentDTO);

    DocumentDTO update(DocumentDTO documentDTO);

    Optional<DocumentDTO> getById(Long id);

    Page<DocumentDTO> findAll(Pageable pageable);

    List<DocumentDTO> findAll();

    void delete(Long id);

    Resource download(String fileUri); //telecharger un fichier via son chemin de stokage



    String storeFile(MultipartFile file, String chemin, String libelle);//charger un fichier sur la plateforme


    public byte[] readFile(String libelle, String chemin)  throws IOException;
    String generateFileName(MultipartFile file);

    String getFileExtension(MultipartFile file) ;

}
