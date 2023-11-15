package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.IndicateurService;
import cil.bf.activiteApp.service.dto.IndicateurDTO;
import cil.bf.activiteApp.utils.PaginationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Zak TEGUERA on 15/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@RestController
@RequestMapping("/api/indicateurs")
public class IndicateurResource {

    private final IndicateurService indicateurService;

    public IndicateurResource(IndicateurService indicateurService) {
        this.indicateurService = indicateurService;
    }

    @Operation(summary = "Ajoute un indicateur", description = "Ajoute un indicateur")
    @ApiResponse(responseCode = "201", description = "Indicateur créé avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<IndicateurDTO> create(@RequestBody IndicateurDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        IndicateurDTO response = indicateurService.create(request);
        return ResponseEntity.created(new URI("/api/indicateurs")).body(response);
    }

    @Operation(summary = "Modifie un indicateur", description = "Modifie un indicateur")
    @PutMapping("/update")
    public ResponseEntity<IndicateurDTO> update(@RequestBody IndicateurDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        IndicateurDTO response = indicateurService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche un indicateur via un ID", description = "Recherche un indicateur via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<IndicateurDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<IndicateurDTO> indicateur = indicateurService.getById(id);
        return ResponseEntity.ok().body(indicateur);
    }

    @Operation(summary = "Liste tous les indicateurs par page", description = "Liste tous les indicateurs par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<IndicateurDTO>> findAll(Pageable pageable) {
        Page<IndicateurDTO> indicateurs = indicateurService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), indicateurs);
        return ResponseEntity.ok().headers(headers).body(indicateurs.getContent());
    }

    @Operation(summary = "Liste tous les indicateurs", description = "Liste tous les indicateurs")
    @GetMapping("/list")
    public ResponseEntity<List<IndicateurDTO>> findAll() {
        List<IndicateurDTO> indicateurs = indicateurService.findAll();
        return ResponseEntity.ok().body(indicateurs);
    }

    @Operation(summary = "Supprime un indicateur via un ID", description = "Supprime un indicateur via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        indicateurService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
