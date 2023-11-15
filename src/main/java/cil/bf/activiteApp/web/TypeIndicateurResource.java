package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.TypeIndicateurService;
import cil.bf.activiteApp.service.dto.TypeIndicateurDTO;
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
@RequestMapping("/api/type-indicateurs")
public class TypeIndicateurResource {

    private final TypeIndicateurService typeIndicateurService;

    public TypeIndicateurResource(TypeIndicateurService typeIndicateurService) {
        this.typeIndicateurService = typeIndicateurService;
    }

    @Operation(summary = "Ajoute un typeIndicateur", description = "Ajoute un typeIndicateur")
    @ApiResponse(responseCode = "201", description = "TypeIndicateur créé avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<TypeIndicateurDTO> create(@RequestBody TypeIndicateurDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        TypeIndicateurDTO response = typeIndicateurService.create(request);
        return ResponseEntity.created(new URI("/api/typeIndicateurs")).body(response);
    }

    @Operation(summary = "Modifie un typeIndicateur", description = "Modifie un typeIndicateur")
    @PutMapping("/update")
    public ResponseEntity<TypeIndicateurDTO> update(@RequestBody TypeIndicateurDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        TypeIndicateurDTO response = typeIndicateurService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche un typeIndicateur via un ID", description = "Recherche un typeIndicateur via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<TypeIndicateurDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<TypeIndicateurDTO> typeIndicateur = typeIndicateurService.getById(id);
        return ResponseEntity.ok().body(typeIndicateur);
    }

    @Operation(summary = "Liste tous les typeIndicateurs par page", description = "Liste tous les typeIndicateurs par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<TypeIndicateurDTO>> findAll(Pageable pageable) {
        Page<TypeIndicateurDTO> typeIndicateurs = typeIndicateurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), typeIndicateurs);
        return ResponseEntity.ok().headers(headers).body(typeIndicateurs.getContent());
    }

    @Operation(summary = "Liste tous les typeIndicateurs", description = "Liste tous les typeIndicateurs")
    @GetMapping("/list")
    public ResponseEntity<List<TypeIndicateurDTO>> findAll() {
        List<TypeIndicateurDTO> typeIndicateurs = typeIndicateurService.findAll();
        return ResponseEntity.ok().body(typeIndicateurs);
    }

    @Operation(summary = "Supprime un typeIndicateur via un ID", description = "Supprime un typeIndicateur via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        typeIndicateurService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
