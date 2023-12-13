package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.TypeDocumentService;
import cil.bf.activiteApp.service.dto.TypeDocumentDTO;
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
@RequestMapping("/api/type-documents")
public class TypeDocumentResource {

    private final TypeDocumentService typeDocumentService;

    public TypeDocumentResource(TypeDocumentService typeDocumentService) {
        this.typeDocumentService = typeDocumentService;
    }

    @Operation(summary = "Ajoute un typeDocument", description = "Ajoute un typeDocument")
    @ApiResponse(responseCode = "201", description = "TypeDocument créé avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<TypeDocumentDTO> create(@RequestBody TypeDocumentDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        TypeDocumentDTO response = typeDocumentService.create(request);
        return ResponseEntity.created(new URI("/api/typeDocuments")).body(response);
    }

    @Operation(summary = "Modifie un typeDocument", description = "Modifie un typeDocument")
    @PutMapping("/update")
    public ResponseEntity<TypeDocumentDTO> update(@RequestBody TypeDocumentDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        TypeDocumentDTO response = typeDocumentService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche un typeDocument via un ID", description = "Recherche un typeDocument via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<TypeDocumentDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<TypeDocumentDTO> typeDocument = typeDocumentService.getById(id);
        return ResponseEntity.ok().body(typeDocument);
    }

    @Operation(summary = "Liste tous les typeDocuments par page", description = "Liste tous les typeDocuments par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<TypeDocumentDTO>> findAll(Pageable pageable) {
        Page<TypeDocumentDTO> typeDocuments = typeDocumentService.findAll(pageable);
        HttpHeaders headers = cil.bf.activiteApp.utils.PaginationUtil.getHeaders(typeDocuments);
        return ResponseEntity.ok().headers(headers).body(typeDocuments.getContent());
    }

    @Operation(summary = "Liste tous les typeDocuments", description = "Liste tous les typeDocuments")
    @GetMapping("/list")
    public ResponseEntity<List<TypeDocumentDTO>> findAll() {
        List<TypeDocumentDTO> typeDocuments = typeDocumentService.findAll();
        return ResponseEntity.ok().body(typeDocuments);
    }

    @Operation(summary = "Supprime un typeDocument via un ID", description = "Supprime un typeDocument via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        typeDocumentService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
