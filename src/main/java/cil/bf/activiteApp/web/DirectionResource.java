/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.DirectionService;
import cil.bf.activiteApp.service.dto.DirectionDTO;
import io.swagger.v3.oas.annotations.Operation;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@RestController
@RequestMapping("/api/directions")
public class DirectionResource {

    private final DirectionService directionService;

    public DirectionResource(DirectionService directionService) {
        this.directionService = directionService;
    }

    @Operation(summary = "Ajoute une direction", description = "Ajoute une direction")
    @PreAuthorize("hasAuthority('ADD_ACTIVITE')")
    @PostMapping("/new")
    public Mono<ResponseEntity<DirectionDTO>> create(@RequestBody DirectionDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        DirectionDTO response = directionService.create(request);
        return Mono.just(ResponseEntity.created(new URI("/api/directions")).body(response));
    }

    @Operation(summary = "Modifie une direction", description = "Modifie une direction")
    @PreAuthorize("hasAuthority('EDIT_ACTIVITE')")
    @PutMapping("/update")
    public Mono<ResponseEntity<DirectionDTO>> update(@RequestBody DirectionDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        DirectionDTO response = directionService.update(request);
        return Mono.just(ResponseEntity.ok().body(response));
    }

    @Operation(summary = "Recherche une direction via un ID", description = "Recherche une direction via un ID")
    @PreAuthorize("hasAuthority('VIEW_ACTIVITE')")
    @GetMapping(path = "/{id}")
    public Mono<ResponseEntity<Optional<DirectionDTO>>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<DirectionDTO> response = directionService.getById(id);
        return Mono.just(ResponseEntity.ok().body(response));
    }

    @Operation(summary = "Liste paginee toutes les directions", description = "Liste paginee toutes les directions")
    @PreAuthorize("hasAuthority('VIEW_ACTIVITE')")
    @GetMapping("/list-page")
    public Mono<ResponseEntity<List<DirectionDTO>>> findAll(Pageable pageable) {
        Page<DirectionDTO> response = directionService.findAll(pageable);
        HttpHeaders headers = cil.bf.activiteApp.utils.PaginationUtil.getHeaders(response);
        return Mono.just(new ResponseEntity<>(response.getContent(), headers, HttpStatus.OK));
    }

    @Operation(summary = "Liste toutes les directions", description = "Liste toutes les directions")
    @PreAuthorize("hasAuthority('VIEW_ACTIVITE')")
    @GetMapping("/list")
    public Mono<ResponseEntity<List<DirectionDTO>>> findAll() {
        List<DirectionDTO> response = directionService.findAll();
        return Mono.just(ResponseEntity.ok().body(response));
    }

    @Operation(summary = "Supprime une direction via un ID", description = "Supprime une direction via un ID")
    @PreAuthorize("hasAuthority('DELETE_ACTIVITE')")
    @DeleteMapping(path = "/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Long id) {
        directionService.delete(id);
        return Mono.just(ResponseEntity.noContent().build());
    }

}
