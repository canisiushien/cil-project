package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.FonctionService;
import cil.bf.activiteApp.service.dto.FonctionDTO;
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
 * KABORE
 */
//@CrossOrigin("*")
@RestController
@RequestMapping("/api/fonctions")
public class FonctionResource {

    private final FonctionService fonctionService;

    public FonctionResource(FonctionService fonctionService) {
        this.fonctionService = fonctionService;
    }

    @Operation(summary = "Ajoute une Fonction", description = "Ajoute une Fonction")
    @ApiResponse(responseCode = "201", description = "Fonction créée avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<FonctionDTO> create(@RequestBody FonctionDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        FonctionDTO response = fonctionService.create(request);
        return ResponseEntity.created(new URI("/api/fonctions")).body(response);
    }

    @Operation(summary = "Modifie une fonction", description = "Modifie une fonction")
    @PutMapping("/update")
    public ResponseEntity<FonctionDTO> update(@RequestBody FonctionDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        FonctionDTO response = fonctionService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche une fonction via un ID", description = "Recherche une fonction via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<FonctionDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<FonctionDTO> fonction = fonctionService.getById(id);
        return ResponseEntity.ok().body(fonction);
    }

    @Operation(summary = "Liste toutes les fonctions par page", description = "Liste toutes les fonctions par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<FonctionDTO>> findAll(Pageable pageable) {
        Page<FonctionDTO> fonctions = fonctionService.findAll(pageable);
        HttpHeaders headers = cil.bf.activiteApp.utils.PaginationUtil.getHeaders(fonctions);
        return ResponseEntity.ok().headers(headers).body(fonctions.getContent());
    }

    @Operation(summary = "Liste toutes les fonctions", description = "Liste de tous les fonctions")
    @GetMapping("/list")
    public ResponseEntity<List<FonctionDTO>> findAll() {
        List<FonctionDTO> fonctions = fonctionService.findAll();
        return ResponseEntity.ok().body(fonctions);
    }

    @Operation(summary = "Supprime une fonction via un ID", description = "Supprime une fonction via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        fonctionService.delete(id);
        return ResponseEntity
                .noContent()
                .build();

    }
}
