package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.TypeReunionService;
import cil.bf.activiteApp.service.dto.TypeReunionDTO;
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
@RequestMapping("/api/type-reunions")
public class TypeReunionResource {

    private final TypeReunionService typeReunionService;

    public TypeReunionResource(TypeReunionService typeReunionService) {
        this.typeReunionService = typeReunionService;
    }

    @Operation(summary = "Ajoute un typeReunion", description = "Ajoute un typeReunion")
    @ApiResponse(responseCode = "201", description = "TypeReunion créé avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<TypeReunionDTO> create(@RequestBody TypeReunionDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        TypeReunionDTO response = typeReunionService.create(request);
        return ResponseEntity.created(new URI("/api/typeReunions")).body(response);
    }

    @Operation(summary = "Modifie un typeReunion", description = "Modifie un typeReunion")
    @PutMapping("/update")
    public ResponseEntity<TypeReunionDTO> update(@RequestBody TypeReunionDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        TypeReunionDTO response = typeReunionService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche un typeReunion via un ID", description = "Recherche un typeReunion via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<TypeReunionDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<TypeReunionDTO> typeReunion = typeReunionService.getById(id);
        return ResponseEntity.ok().body(typeReunion);
    }

    @Operation(summary = "Liste tous les typeReunions par page", description = "Liste tous les typeReunions par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<TypeReunionDTO>> findAll(Pageable pageable) {
        Page<TypeReunionDTO> typeReunions = typeReunionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), typeReunions);
        return ResponseEntity.ok().headers(headers).body(typeReunions.getContent());
    }

    @Operation(summary = "Liste tous les typeReunions", description = "Liste tous les typeReunions")
    @GetMapping("/list")
    public ResponseEntity<List<TypeReunionDTO>> findAll() {
        List<TypeReunionDTO> typeReunions = typeReunionService.findAll();
        return ResponseEntity.ok().body(typeReunions);
    }

    @Operation(summary = "Supprime un typeReunion via un ID", description = "Supprime un typeReunion via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        typeReunionService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
