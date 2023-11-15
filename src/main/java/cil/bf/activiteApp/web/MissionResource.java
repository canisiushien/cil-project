package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.MissionService;
import cil.bf.activiteApp.service.dto.MissionDTO;
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
@RequestMapping("/api/missions")
public class MissionResource {

    private final MissionService missionService;

    public MissionResource(MissionService missionService) {
        this.missionService = missionService;
    }

    @Operation(summary = "Ajoute une mission", description = "Ajoute une mission")
    @ApiResponse(responseCode = "201", description = "Mission créée avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<MissionDTO> create(@RequestBody MissionDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        MissionDTO response = missionService.create(request);
        return ResponseEntity.created(new URI("/api/missions")).body(response);
    }

    @Operation(summary = "Modifie une mission", description = "Modifie une mission")
    @PutMapping("/update")
    public ResponseEntity<MissionDTO> update(@RequestBody MissionDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        MissionDTO response = missionService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche une mission via un ID", description = "Recherche une mission via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<MissionDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<MissionDTO> mission = missionService.getById(id);
        return ResponseEntity.ok().body(mission);
    }

    @Operation(summary = "Liste toutes les missions par page", description = "Liste toutes les missions par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<MissionDTO>> findAll(Pageable pageable) {
        Page<MissionDTO> missions = missionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), missions);
        return ResponseEntity.ok().headers(headers).body(missions.getContent());
    }

    @Operation(summary = "Liste tous les missions", description = "Liste tous les missions")
    @GetMapping("/list")
    public ResponseEntity<List<MissionDTO>> findAll() {
        List<MissionDTO> missions = missionService.findAll();
        return ResponseEntity.ok().body(missions);
    }

    @Operation(summary = "Supprime une mission via un ID", description = "Supprime une mission via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        missionService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
