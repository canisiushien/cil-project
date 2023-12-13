package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.ObjectifStrategiqueService;
import cil.bf.activiteApp.service.dto.ObjectifStrategiqueDTO;
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
@RequestMapping("/api/objectif-strategiques")
public class ObjectifStrategiqueResource {

    private final ObjectifStrategiqueService objectifStrategiqueService;

    public ObjectifStrategiqueResource(ObjectifStrategiqueService objectifStrategiqueService) {
        this.objectifStrategiqueService = objectifStrategiqueService;
    }

    @Operation(summary = "Ajoute un objectifStrategique", description = "Ajoute un objectifStrategique")
    @ApiResponse(responseCode = "201", description = "ObjectifStrategique créé avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<ObjectifStrategiqueDTO> create(@RequestBody ObjectifStrategiqueDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        ObjectifStrategiqueDTO response = objectifStrategiqueService.create(request);
        return ResponseEntity.created(new URI("/api/objectif-strategiques")).body(response);
    }

    @Operation(summary = "Modifie un objectifStrategique", description = "Modifie un objectifStrategique")
    @PutMapping("/update")
    public ResponseEntity<ObjectifStrategiqueDTO> update(@RequestBody ObjectifStrategiqueDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        ObjectifStrategiqueDTO response = objectifStrategiqueService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche un objectifStrategique via un ID", description = "Recherche un objectifStrategique via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<ObjectifStrategiqueDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<ObjectifStrategiqueDTO> objectifStrategique = objectifStrategiqueService.getById(id);
        return ResponseEntity.ok().body(objectifStrategique);
    }

    @Operation(summary = "Liste tous les objectifStrategiques par page", description = "Liste tous les objectifStrategiques par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<ObjectifStrategiqueDTO>> findAll(Pageable pageable) {
        Page<ObjectifStrategiqueDTO> objectifStrategiques = objectifStrategiqueService.findAll(pageable);
        HttpHeaders headers = cil.bf.activiteApp.utils.PaginationUtil.getHeaders(objectifStrategiques);
        return ResponseEntity.ok().headers(headers).body(objectifStrategiques.getContent());
    }

    @Operation(summary = "Liste tous les objectifStrategiques", description = "Liste tous les objectifStrategiques")
    @GetMapping("/list")
    public ResponseEntity<List<ObjectifStrategiqueDTO>> findAll() {
        List<ObjectifStrategiqueDTO> objectifStrategiques = objectifStrategiqueService.findAll();
        return ResponseEntity.ok().body(objectifStrategiques);
    }

    @Operation(summary = "Supprime un objectifStrategique via un ID", description = "Supprime un objectifStrategique via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        objectifStrategiqueService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
