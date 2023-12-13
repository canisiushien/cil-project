package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.ActiviteService;
import cil.bf.activiteApp.service.DocumentService;
import cil.bf.activiteApp.service.dto.ActiviteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Zak TEGUERA on 15/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@RestController
@RequestMapping("/api/activites")
public class ActiviteResource {

    private final ActiviteService activiteService;
    private final DocumentService documentService;

    public ActiviteResource(ActiviteService activiteService, DocumentService documentService) {
        this.activiteService = activiteService;
        this.documentService = documentService;
    }

    @Operation(summary = "Ajoute une activite", description = "Ajoute une activite")
    @ApiResponse(responseCode = "201", description = "Activite créée avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<ActiviteDTO> create(@RequestBody ActiviteDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        ActiviteDTO response = activiteService.create(request);
        return ResponseEntity.created(new URI("/api/activites")).body(response);
    }

    @Operation(summary = "Modifie une activite", description = "Modifie une activite")
    @PutMapping("/update")
    public ResponseEntity<ActiviteDTO> update(@RequestBody ActiviteDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        ActiviteDTO response = activiteService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche une activite via un ID", description = "Recherche une activite via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<ActiviteDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<ActiviteDTO> activite = activiteService.getById(id);
        return ResponseEntity.ok().body(activite);
    }

    @Operation(summary = "Liste toutes les activites par page", description = "Liste toutes les activites par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<ActiviteDTO>> findAll(Pageable pageable) {
        Page<ActiviteDTO> activites = activiteService.findAll(pageable);
        HttpHeaders headers = cil.bf.activiteApp.utils.PaginationUtil.getHeaders(activites);
        return ResponseEntity.ok().headers(headers).body(activites.getContent());
    }

    @Operation(summary = "Liste toutes les activites", description = "Liste toutes les activites")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<ActiviteDTO>> findAll() {
        List<ActiviteDTO> activites = activiteService.findAll();
        return ResponseEntity.ok().body(activites);
    }

    @Operation(summary = "Supprime une activite via un ID", description = "Supprime une activite via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        activiteService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @PutMapping("/{id}/cloturer")
    public ResponseEntity<ActiviteDTO> cloturer(@PathVariable(value = "id") Long id, @RequestParam("file") MultipartFile file) throws URISyntaxException {

        if (id == null || file.isEmpty()) {
            throw new UpdateElementException();
        }

        ActiviteDTO response = activiteService.cloturer(id, file);
        return ResponseEntity.ok().body(response);
    }

}
