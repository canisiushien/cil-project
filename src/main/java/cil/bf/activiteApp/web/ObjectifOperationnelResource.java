package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.ObjectifOperationnelService;
import cil.bf.activiteApp.service.dto.ObjectifOperationnelDTO;
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
@RequestMapping("/api/objectif-operationnels")
public class ObjectifOperationnelResource {

    private final ObjectifOperationnelService objectifOperationnelService;

    public ObjectifOperationnelResource(ObjectifOperationnelService objectifOperationnelService) {
        this.objectifOperationnelService = objectifOperationnelService;
    }

    @Operation(summary = "Ajoute un objectifOperationnel", description = "Ajoute un objectifOperationnel")
    @ApiResponse(responseCode = "201", description = "ObjectifOperationnel créé avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<ObjectifOperationnelDTO> create(@RequestBody ObjectifOperationnelDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        ObjectifOperationnelDTO response = objectifOperationnelService.create(request);
        return ResponseEntity.created(new URI("/api/objectif-operationnels")).body(response);
    }

    @Operation(summary = "Modifie un objectifOperationnel", description = "Modifie un objectifOperationnel")
    @PutMapping("/update")
    public ResponseEntity<ObjectifOperationnelDTO> update(@RequestBody ObjectifOperationnelDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        ObjectifOperationnelDTO response = objectifOperationnelService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche un objectifOperationnel via un ID", description = "Recherche un objectifOperationnel via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<ObjectifOperationnelDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<ObjectifOperationnelDTO> objectifOperationnel = objectifOperationnelService.getById(id);
        return ResponseEntity.ok().body(objectifOperationnel);
    }

    @Operation(summary = "Liste tous les objectifOperationnels par page", description = "Liste tous les objectifOperationnels par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<ObjectifOperationnelDTO>> findAll(Pageable pageable) {
        Page<ObjectifOperationnelDTO> objectifOperationnels = objectifOperationnelService.findAll(pageable);
        HttpHeaders headers = cil.bf.activiteApp.utils.PaginationUtil.getHeaders(objectifOperationnels);
        return ResponseEntity.ok().headers(headers).body(objectifOperationnels.getContent());
    }

    @Operation(summary = "Liste tous les objectifOperationnels", description = "Liste tous les objectifOperationnels")
    @GetMapping("/list")
    public ResponseEntity<List<ObjectifOperationnelDTO>> findAll() {
        List<ObjectifOperationnelDTO> objectifOperationnels = objectifOperationnelService.findAll();
        return ResponseEntity.ok().body(objectifOperationnels);
    }

    @Operation(summary = "Supprime un objectifOperationnel via un ID", description = "Supprime un objectifOperationnel via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        objectifOperationnelService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
