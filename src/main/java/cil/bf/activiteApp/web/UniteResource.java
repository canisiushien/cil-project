package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.UniteService;
import cil.bf.activiteApp.service.dto.UniteDTO;
import cil.bf.activiteApp.utils.PaginationUtil;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by Zak TEGUERA on 15/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@RestController
@RequestMapping("/api/unites")
public class UniteResource {

    private final UniteService uniteService;

    public UniteResource(UniteService uniteService) {
        this.uniteService = uniteService;
    }

    @Operation(summary = "Ajoute une unite", description = "Ajoute une unite")
    @ApiResponse(responseCode = "201", description = "Unite créée avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<UniteDTO> create(@RequestBody UniteDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        UniteDTO response = uniteService.create(request);
        return ResponseEntity.created(new URI("/api/unites")).body(response);
    }

    @Operation(summary = "Modifie une unite", description = "Modifie une unite")
    @PutMapping("/update")
    public ResponseEntity<UniteDTO> update(@RequestBody UniteDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        UniteDTO response = uniteService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche une unite via un ID", description = "Recherche une unite via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<UniteDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<UniteDTO> unite = uniteService.getById(id);
        return ResponseEntity.ok().body(unite);
    }

    @Operation(summary = "Liste toutes les unites par page", description = "Liste toutes les unites par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<UniteDTO>> findAll(Pageable pageable) {
        Page<UniteDTO> unites = uniteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), unites);
        return ResponseEntity.ok().headers(headers).body(unites.getContent());
    }

    @Operation(summary = "Liste toutes les unites", description = "Liste toutes les unites")
    @GetMapping("/list")
    public ResponseEntity<List<UniteDTO>> findAll() {
        List<UniteDTO> unites = uniteService.findAll();
        return ResponseEntity.ok().body(unites);
    }

    @Operation(summary = "Supprime une unite via un ID", description = "Supprime une unite via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        uniteService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
