package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.MandatControleService;
import cil.bf.activiteApp.service.dto.MandatControleDTO;
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
@RequestMapping("/api/mandat-controles")
public class MandatControleResource {

    private final MandatControleService mandatControleService;

    public MandatControleResource(MandatControleService mandatControleService) {
        this.mandatControleService = mandatControleService;
    }

    @Operation(summary = "Ajoute un mandatControle", description = "Ajoute un mandatControle")
    @ApiResponse(responseCode = "201", description = "MandatControle créé avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<MandatControleDTO> create(@RequestBody MandatControleDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        MandatControleDTO response = mandatControleService.create(request);
        return ResponseEntity.created(new URI("/api/mandat-controles")).body(response);
    }

    @Operation(summary = "Modifie un mandatControle", description = "Modifie un mandatControle")
    @PutMapping("/update")
    public ResponseEntity<MandatControleDTO> update(@RequestBody MandatControleDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        MandatControleDTO response = mandatControleService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche un mandatControle via un ID", description = "Recherche un mandatControle via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<MandatControleDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<MandatControleDTO> mandatControle = mandatControleService.getById(id);
        return ResponseEntity.ok().body(mandatControle);
    }

    @Operation(summary = "Liste tous les mandatControles par page", description = "Liste tous les mandatControles par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<MandatControleDTO>> findAll(Pageable pageable) {
        Page<MandatControleDTO> mandatControles = mandatControleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), mandatControles);
        return ResponseEntity.ok().headers(headers).body(mandatControles.getContent());
    }

    @Operation(summary = "Liste tous les mandatControles", description = "Liste tous les mandatControles")
    @GetMapping("/list")
    public ResponseEntity<List<MandatControleDTO>> findAll() {
        List<MandatControleDTO> mandatControles = mandatControleService.findAll();
        return ResponseEntity.ok().body(mandatControles);
    }

    @Operation(summary = "Supprime un mandatControle via un ID", description = "Supprime un mandatControle via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mandatControleService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
