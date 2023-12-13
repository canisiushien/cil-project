package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.DocumentService;
import cil.bf.activiteApp.service.dto.DocumentDTO;
import cil.bf.activiteApp.utils.HeaderUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Zak TEGUERA on 15/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@Slf4j
@RestController
@RequestMapping("/api/documents")
public class DocumentResource {

    private final DocumentService documentService;
    private final Path root = Paths.get("C:\\pieces");
    private static final String ENTITY_NAME = "document";

    public DocumentResource(DocumentService documentService) {
        this.documentService = documentService;
    }

    @Operation(summary = "Ajoute un document", description = "Ajoute un document")
    @ApiResponse(responseCode = "201", description = "Document créé avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    // @PostMapping(value = "/new", consumes = {"application/pdf"})
    @PostMapping(value = "/new",
            headers = "Content-Type= multipart/form-data",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<DocumentDTO> createDocument(@Valid @RequestPart DocumentDTO documentDTO, @RequestPart MultipartFile file) throws URISyntaxException {
        log.debug("REST request to save Document : {}", documentDTO);
        String basePath = root + "CIL/";
        if (documentDTO.getId() != null) {
            throw new CreateNewElementException();
        }
        if (!file.isEmpty()) {
            String filename = documentService.generateFileName(file);
            documentDTO.setChemin(basePath + filename);
            documentDTO.setLibelle(filename);
            String extension = documentService.getFileExtension(file);
            documentDTO.setFormat(extension);
            documentService.storeFile(file, basePath, filename);
        }
        DocumentDTO result = documentService.create(documentDTO);
        return ResponseEntity.created(new URI("/api/documents/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert("CILAPP", false, ENTITY_NAME, result.getLibelle()))
                .body(result);
    }

    @Operation(summary = "Modifie un document", description = "Modifie un document")
    @PutMapping("/update")
    public ResponseEntity<DocumentDTO> update(@RequestBody DocumentDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        DocumentDTO response = documentService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche un document via un ID", description = "Recherche un document via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<DocumentDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<DocumentDTO> document = documentService.getById(id);
        return ResponseEntity.ok().body(document);
    }

    @Operation(summary = "Liste tous les documents par page", description = "Liste tous les documents par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<DocumentDTO>> findAll(Pageable pageable) {
        Page<DocumentDTO> documents = documentService.findAll(pageable);
        HttpHeaders headers = cil.bf.activiteApp.utils.PaginationUtil.getHeaders(documents);
        return ResponseEntity.ok().headers(headers).body(documents.getContent());
    }

    @Operation(summary = "Liste tous les documents", description = "Liste tous les documents")
    @GetMapping("/list")
    public ResponseEntity<List<DocumentDTO>> findAll() {
        List<DocumentDTO> documents = documentService.findAll();
        return ResponseEntity.ok().body(documents);
    }

    @Operation(summary = "Supprime un document via un ID", description = "Supprime un document via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        documentService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    /**
     *
     * @param request
     * @param fileUri
     * @return
     * @throws URISyntaxException
     */
    @Operation(summary = "Telecharge un document via un chemin d'acces", description = "Telecharge un document via un chemin d'acces")
    @GetMapping(value = "/download/{fileUri}")
    public ResponseEntity<Resource> downloadDocument(HttpServletRequest request, @PathVariable(name = "fileUri", required = true) String fileUri) throws URISyntaxException {
        Resource file = documentService.download(fileUri);

        //determiner le type de fichier
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(file.getFile().getAbsolutePath());
        } catch (IOException e) {
            log.info("Impossible de determiner le type de fichier !");
        }

        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);

    }

    /*
    @PostMapping(path = "/upload")
    public ResponseEntity<DocumentDTO> createDocument(@Valid @RequestPart DocumentDTO documentDTO, @RequestPart MultipartFile file) throws URISyntaxException {
        log.debug("REST request to save Document : {}", documentDTO);
        String basePath = storage + "casier-judiciaire/";
        if (document.getId() != null) {
            throw new BadRequestAlertException("A new document cannot already have an ID", ENTITY_NAME, "idexists");
        }
        if (!multipartFile.isEmpty()) {
            String filename = fileStoreService.generateFileName(multipartFile);
            document.setUrl(filename);
            fileStoreService.storeFile(multipartFile, basePath, filename);
        }
        Document result = documentService.save(document);
        return ResponseEntity.created(new URI("/api/documents/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getLibelle()))
                .body(result);
    }
    /*
    public ResponseEntity<DocumentDTO> uploadFile(@RequestPart DocumentDTO documentDTO,@RequestPart List<MultipartFile> file) throws IOException {
        log.info("Request to read file : {}", documentDTO);
        String chemin = documentDTO.getChemin() + "/";
        String libelle = documentDTO.getLibelle();
         documentService.readFile(libelle,chemin);
        HttpHeaders headers = new HttpHeaders();
        documentDTO.setData(file);
        return ResponseEntity.ok().body(documentDTO);
    }
     */
 /*
    @PostMapping("/upload")
    public ResponseEntity<Resource> uploadFile(@RequestBody DocumentDTO request,@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(file.getResource());
            }
            Resource fichier = documentService.storeFile(file);

            //
            DocumentDTO response = documentService.create(request);


            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(fichier);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(file.getResource());
        }
    }
     */
}
