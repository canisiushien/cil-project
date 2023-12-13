package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.ActionService;
import cil.bf.activiteApp.service.dto.ActionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

/**
 * Created by Zak TEGUERA on 15/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@RestController
@RequestMapping("/api/actions")
public class ActionResource {

    private final ActionService actionService;

    public ActionResource(ActionService actionService) {
        this.actionService = actionService;
    }

    @Operation(summary = "Ajoute une action", description = "Ajoute une action")
    @ApiResponse(responseCode = "201", description = "Action créée avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<ActionDTO> create(@RequestBody ActionDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        ActionDTO response = actionService.create(request);
        return ResponseEntity.created(new URI("/api/actions")).body(response);
    }

    @Operation(summary = "Modifie une action", description = "Modifie une action")
    @PutMapping("/update")
    public ResponseEntity<ActionDTO> update(@RequestBody ActionDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        ActionDTO response = actionService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche une action via un ID", description = "Recherche une action via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<ActionDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<ActionDTO> action = actionService.getById(id);
        return ResponseEntity.ok().body(action);
    }

    @Operation(summary = "Liste toutes les actions", description = "Liste toutes les actions")
    @GetMapping("/list-page")
    public ResponseEntity<List<ActionDTO>> findAll(Pageable pageable) {
        Page<ActionDTO> actions = actionService.findAll(pageable);
        HttpHeaders headers = cil.bf.activiteApp.utils.PaginationUtil.getHeaders(actions);
        return new ResponseEntity<>(actions.getContent(), headers, HttpStatus.OK);
    }

    @Operation(summary = "Liste toutes les actions", description = "Liste toutes les actions")
    @GetMapping("/list")
    public ResponseEntity<List<ActionDTO>> findAll() {
        List<ActionDTO> actions = actionService.findAll();
        return ResponseEntity.ok().body(actions);
    }

    @Operation(summary = "Supprime une action via un ID", description = "Supprime une action via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        actionService.delete(id);
        return ResponseEntity
                .noContent()
                .build();

    }
}
