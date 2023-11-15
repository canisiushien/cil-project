package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.TypeSessionService;
import cil.bf.activiteApp.service.dto.TypeSessionDTO;
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
@RequestMapping("/api/type-sessions")
public class TypeSessionResource {

    private final TypeSessionService typeSessionService;

    public TypeSessionResource(TypeSessionService typeSessionService) {
        this.typeSessionService = typeSessionService;
    }

    @Operation(summary = "Ajoute un typeSession", description = "Ajoute un typeSession")
    @ApiResponse(responseCode = "201", description = "TypeSession créé avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<TypeSessionDTO> create(@RequestBody TypeSessionDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        TypeSessionDTO response = typeSessionService.create(request);
        return ResponseEntity.created(new URI("/api/typeSessions")).body(response);
    }

    @Operation(summary = "Modifie un typeSession", description = "Modifie un typeSession")
    @PutMapping("/update")
    public ResponseEntity<TypeSessionDTO> update(@RequestBody TypeSessionDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        TypeSessionDTO response = typeSessionService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche un typeSession via un ID", description = "Recherche un typeSession via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<TypeSessionDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<TypeSessionDTO> typeSession = typeSessionService.getById(id);
        return ResponseEntity.ok().body(typeSession);
    }

    @Operation(summary = "Liste tous les typeSessions par page", description = "Liste tous les typeSessions par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<TypeSessionDTO>> findAll(Pageable pageable) {
        Page<TypeSessionDTO> typeSessions = typeSessionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), typeSessions);
        return ResponseEntity.ok().headers(headers).body(typeSessions.getContent());
    }

    @Operation(summary = "Liste tous les typeSessions", description = "Liste tous les typeSessions")
    @GetMapping("/list")
    public ResponseEntity<List<TypeSessionDTO>> findAll() {
        List<TypeSessionDTO> typeSessions = typeSessionService.findAll();
        return ResponseEntity.ok().body(typeSessions);
    }

    @Operation(summary = "Supprime un typeSession via un ID", description = "Supprime un typeSession via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        typeSessionService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
