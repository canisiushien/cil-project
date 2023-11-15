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
import cil.bf.activiteApp.utils.PaginationUtil;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    /**
     *
     * @param request
     * @return
     * @throws URISyntaxException
     */
    @Operation(summary = "Ajoute une direction", description = "Ajoute une direction")
    @PostMapping("/new")
    public ResponseEntity<DirectionDTO> create(@RequestBody DirectionDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        DirectionDTO response = directionService.create(request);
        return ResponseEntity.created(new URI("/api/directions")).body(response);
    }

    /**
     *
     * @param request
     * @return
     * @throws URISyntaxException
     */
    @Operation(summary = "Modifie une direction", description = "Modifie une direction")
    @PutMapping("/update")
    public ResponseEntity<DirectionDTO> update(@RequestBody DirectionDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        DirectionDTO response = directionService.update(request);
        return ResponseEntity.ok().body(response);
    }

    /**
     *
     * @param id
     * @return
     */
    @Operation(summary = "Recherche une direction via un ID", description = "Recherche une direction via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<DirectionDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<DirectionDTO> response = directionService.getById(id);
        return ResponseEntity.ok().body(response);
    }

    /**
     *
     * @param pageable
     * @return
     */
    @Operation(summary = "Liste paginee toutes les directions", description = "Liste paginee toutes les directions")
    @GetMapping("/list-page")
    public ResponseEntity<List<DirectionDTO>> findAll(Pageable pageable) {
        Page<DirectionDTO> response = directionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), response);
        return new ResponseEntity<>(response.getContent(), headers, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @Operation(summary = "Liste toutes les directions", description = "Liste toutes les directions")
    @GetMapping("/list")
    public ResponseEntity<List<DirectionDTO>> findAll() {
        List<DirectionDTO> response = directionService.findAll();
        return ResponseEntity.ok().body(response);
    }

    /**
     *
     * @param id
     * @return
     */
    @Operation(summary = "Supprime une direction via un ID", description = "Supprime une direction via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        directionService.delete(id);
        return ResponseEntity
                .noContent()
                .build();

    }
}
