package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.InterimService;
import cil.bf.activiteApp.service.dto.InterimDTO;
import cil.bf.activiteApp.utils.PaginationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/interims")
public class InterimResource {

    private final InterimService interimService;

    public InterimResource(InterimService interimService) {
        this.interimService = interimService;
    }

    @Operation(summary = "Ajoute un interim", description = "Ajoute un interim")
    @ApiResponse(responseCode = "201", description = "Interim créé avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<InterimDTO> create(@RequestBody InterimDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        InterimDTO response = interimService.create(request);
        return ResponseEntity.created(new URI("/api/interims")).body(response);
    }

    @Operation(summary = "Modifie un interim", description = "Modifie un interim")
    @PutMapping("/update")
    public ResponseEntity<InterimDTO> update(@RequestBody InterimDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        InterimDTO response = interimService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche un interim via un ID", description = "Recherche un interim via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<InterimDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<InterimDTO> interim = interimService.getById(id);
        return ResponseEntity.ok().body(interim);
    }

    @Operation(summary = "Liste tous les interims par page", description = "Liste tous les interims par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<InterimDTO>> findAll(Pageable pageable) {
        Page<InterimDTO> interims = interimService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), interims);
        return new ResponseEntity<>(interims.getContent(), headers, HttpStatus.OK);
    }

    @Operation(summary = "Liste tous les interims", description = "Liste tous les interims")
    @GetMapping("/list")
    public ResponseEntity<List<InterimDTO>> findAll() {
        List<InterimDTO> interims = interimService.findAll();
        return ResponseEntity.ok().body(interims);
    }

    @Operation(summary = "Supprime un interim via un ID", description = "Supprime un interim via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        interimService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
