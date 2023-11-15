package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.PeriodiciteService;
import cil.bf.activiteApp.service.dto.PeriodiciteDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import cil.bf.activiteApp.utils.PaginationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by Zak TEGUERA on 15/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@RestController
@RequestMapping("/api/periodicites")
public class PeriodiciteResource {

    private final PeriodiciteService periodiciteService;

    public PeriodiciteResource(PeriodiciteService periodiciteService) {
        this.periodiciteService = periodiciteService;
    }

    @Operation(summary = "Ajoute une periodicite", description = "Ajoute une periodicite")
    @ApiResponse(responseCode = "201", description = "Periodicite créée avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<PeriodiciteDTO> create(@RequestBody PeriodiciteDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        PeriodiciteDTO response = periodiciteService.create(request);
        return ResponseEntity.created(new URI("/api/periodicites")).body(response);
    }

    @Operation(summary = "Modifie une periodicite", description = "Modifie une periodicite")
    @PutMapping("/update")
    public ResponseEntity<PeriodiciteDTO> update(@RequestBody PeriodiciteDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        PeriodiciteDTO response = periodiciteService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche une periodicite via un ID", description = "Recherche une periodicite via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<PeriodiciteDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<PeriodiciteDTO> periodicite = periodiciteService.getById(id);
        return ResponseEntity.ok().body(periodicite);
    }

    @Operation(summary = "Liste toutes les periodicites par page", description = "Liste toutes les periodicites par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<PeriodiciteDTO>> findAll(Pageable pageable) {
        Page<PeriodiciteDTO> periodicites = periodiciteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), periodicites);
        return ResponseEntity.ok().headers(headers).body(periodicites.getContent());
    }

    @Operation(summary = "Liste toutes les periodicites", description = "Liste toutes les periodicites")
    @GetMapping("/list")
    public ResponseEntity<List<PeriodiciteDTO>> findAll() {
        List<PeriodiciteDTO> periodicites = periodiciteService.findAll();
        return ResponseEntity.ok().body(periodicites);
    }

    @Operation(summary = "Supprime une periodicite via un ID", description = "Supprime une periodicite via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        periodiciteService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
