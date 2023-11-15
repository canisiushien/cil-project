package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.NotificationService;
import cil.bf.activiteApp.service.dto.NotificationDTO;
import cil.bf.activiteApp.utils.PaginationUtil;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by Zak TEGUERA on 18/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@RestController
@RequestMapping("/api/notifications")
public class NotificationResource {

    private final NotificationService notificationService;

    public NotificationResource(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Operation(summary = "Ajoute une notification", description = "Ajoute une notification")
    @ApiResponse(responseCode = "201", description = "Notification créée avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<NotificationDTO> create(@RequestBody NotificationDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        NotificationDTO response = notificationService.create(request);
        return ResponseEntity.created(new URI("/api/notifications")).body(response);
    }

    @Operation(summary = "Modifie une notification", description = "Modifie une notification")
    @PutMapping("/update")
    public ResponseEntity<NotificationDTO> update(@RequestBody NotificationDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        NotificationDTO response = notificationService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche une notification via un ID", description = "Recherche une notification via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<NotificationDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<NotificationDTO> notification = notificationService.getById(id);
        return ResponseEntity.ok().body(notification);
    }

    @Operation(summary = "Liste toutes les notifications par page", description = "Liste toutes les notifications par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<NotificationDTO>> findAll(Pageable pageable) {
        Page<NotificationDTO> notifications = notificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), notifications);
        return new ResponseEntity<>(notifications.getContent(), headers, HttpStatus.OK);
    }

    @Operation(summary = "Liste toutes les notifications", description = "Liste toutes les notifications")
    @GetMapping("/list")
    public ResponseEntity<List<NotificationDTO>> findAll() {
        List<NotificationDTO> notifications = notificationService.findAll();
        return ResponseEntity.ok().body(notifications);
    }

    @Operation(summary = "Liste toutes les notifications d'un utilisateur", description = "Liste toutes les notifications d'un utilisateur")
    @GetMapping(path = "/user/list/{userId}")
    public ResponseEntity<List<NotificationDTO>> findAllByUser(@PathVariable Long userId, Pageable pageable) {
        Page<NotificationDTO> notifications = notificationService.findAllByUser(userId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), notifications);
        return new ResponseEntity<>(notifications.getContent(), headers, HttpStatus.OK);
    }

    @Operation(summary = "Liste toutes les notifications non lues d'un utilisateur", description = "Liste toutes les notifications non lues d'un utilisateur")
    @GetMapping(path = "/user/unread/list/{userId}")
    public ResponseEntity<List<NotificationDTO>> findAllUnreadByUser(@PathVariable Long userId, Pageable pageable) {
        Page<NotificationDTO> notifications = notificationService.findAllUnreadByUser(userId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), notifications);
        return new ResponseEntity<>(notifications.getContent(), headers, HttpStatus.OK);
    }

    @Operation(summary = "Supprime une notification via un ID", description = "Supprime une notification via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        notificationService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
