package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.ExerciceService;
import cil.bf.activiteApp.service.dto.ExerciceDTO;
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
 * Created by Zak TEGUERA on 14/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@RestController
@RequestMapping("/api/exercices")
public class ExerciceResource {

    private final ExerciceService exerciceService;

    public ExerciceResource(ExerciceService exerciceService) {
        this.exerciceService = exerciceService;
    }

    @Operation(summary = "Ajoute un exercice", description = "Ajoute un exercice")
    @ApiResponse(responseCode = "201", description = "Exercice créé avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<ExerciceDTO> create(@RequestBody ExerciceDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        ExerciceDTO response = exerciceService.create(request);
        return ResponseEntity.created(new URI("/api/exercices")).body(response);
    }

    @Operation(summary = "Modifie un exercice", description = "Modifie un exercice")
    @PutMapping("/update")
    public ResponseEntity<ExerciceDTO> update(@RequestBody ExerciceDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        ExerciceDTO response = exerciceService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche un exercice via un ID", description = "Recherche un exercice via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<ExerciceDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<ExerciceDTO> exercice = exerciceService.getById(id);
        return ResponseEntity.ok().body(exercice);
    }

    @Operation(summary = "Liste tous les exercices par page", description = "Liste tous les exercices par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<ExerciceDTO>> findAll(Pageable pageable) {
        Page<ExerciceDTO> exercices = exerciceService.findAll(pageable);
        HttpHeaders headers = cil.bf.activiteApp.utils.PaginationUtil.getHeaders(exercices);
        return ResponseEntity.ok().headers(headers).body(exercices.getContent());
    }

    @Operation(summary = "Liste tous les exercices", description = "Liste tous les exercices")
    @GetMapping("/list")
    public ResponseEntity<List<ExerciceDTO>> findAll() {
        List<ExerciceDTO> exercices = exerciceService.findAll();
        return ResponseEntity.ok().body(exercices);
    }

    @Operation(summary = "Supprime un exercice via un ID", description = "Supprime un exercice via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        exerciceService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
