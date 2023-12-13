package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.ProgrammeService;
import cil.bf.activiteApp.service.dto.ProgrammeDTO;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Zak TEGUERA on 15/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@RestController
@RequestMapping("/api/programmes")
public class ProgrammeResource {

    private final ProgrammeService programmeService;

    public ProgrammeResource(ProgrammeService programmeService) {
        this.programmeService = programmeService;
    }

    @Operation(summary = "Ajoute un programme", description = "Ajoute un programme")
    @ApiResponse(responseCode = "201", description = "Programme créé avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<ProgrammeDTO> create(@RequestBody ProgrammeDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        ProgrammeDTO response = programmeService.create(request);
        return ResponseEntity.created(new URI("/api/programmes")).body(response);
    }

    @Operation(summary = "Modifie un programme", description = "Modifie un programme")
    @PutMapping("/update")
    public ResponseEntity<ProgrammeDTO> update(@RequestBody ProgrammeDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        ProgrammeDTO response = programmeService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche un programme via un ID", description = "Recherche un programme via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<ProgrammeDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<ProgrammeDTO> programme = programmeService.getById(id);
        return ResponseEntity.ok().body(programme);
    }

    @Operation(summary = "Liste tous les programmes par page", description = "Liste tous les programmes par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<ProgrammeDTO>> findAll(Pageable pageable) {
        Page<ProgrammeDTO> programmes = programmeService.findAll(pageable);
        HttpHeaders headers = cil.bf.activiteApp.utils.PaginationUtil.getHeaders(programmes);
        return ResponseEntity.ok().headers(headers).body(programmes.getContent());
    }

    @Operation(summary = "Liste tous les programmes", description = "Liste tous les programmes")
    @GetMapping("/list")
    public ResponseEntity<List<ProgrammeDTO>> findAll() {
        List<ProgrammeDTO> programmes = programmeService.findAll();
        return ResponseEntity.ok().body(programmes);
    }

    @Operation(summary = "Supprime un programme via un ID", description = "Supprime un programme via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        programmeService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
