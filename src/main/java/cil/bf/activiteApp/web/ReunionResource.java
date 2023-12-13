package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.CreateNewElementException;
import cil.bf.activiteApp.exception.UpdateElementException;
import cil.bf.activiteApp.service.MailService;
import cil.bf.activiteApp.service.ReunionService;
import cil.bf.activiteApp.service.dto.ReunionDTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Zak TEGUERA on 15/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@RestController
@RequestMapping("/api/reunions")
public class ReunionResource {

    private final ReunionService reunionService;

    private final MailService mailService;

    public ReunionResource(ReunionService reunionService, MailService mailService) {
        this.reunionService = reunionService;
        this.mailService = mailService;
    }

    @Operation(summary = "Ajoute une reunion", description = "Ajoute une reunion")
    @ApiResponse(responseCode = "201", description = "Reunion créée avec succès")
    @ApiResponse(responseCode = "401")
    @ApiResponse(responseCode = "409", description = "Conflict, une donnée similaire existe déjà")
    @ApiResponse(responseCode = "500", description = "Le serveur n'est pas en mesure de traiter la situation qu'il rencontre")
    @PostMapping("/new")
    public ResponseEntity<ReunionDTO> create(@RequestBody ReunionDTO request) throws URISyntaxException {
        if (request.getId() != null) {
            throw new CreateNewElementException();
        }
        ReunionDTO response = reunionService.create(request);
        return ResponseEntity.created(new URI("/api/reunions")).body(response);
        /*
        List<ReunionDTO> list = reunionService.findByTypeReunion("REUNION DE CABINET");
        return ResponseEntity.created(new URI("/api/reunions")).body(list);*/
    }

    @Operation(summary = "Modifie une reunion", description = "Modifie une reunion")
    @PutMapping("/update")
    public ResponseEntity<ReunionDTO> update(@RequestBody ReunionDTO request) throws URISyntaxException {
        if (request.getId() == null) {
            throw new UpdateElementException();
        }
        ReunionDTO response = reunionService.update(request);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Recherche une reunion via un ID", description = "Recherche une reunion via un ID")
    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<ReunionDTO>> get(@PathVariable(name = "id", required = true) Long id) {
        Optional<ReunionDTO> reunion = reunionService.getById(id);
        return ResponseEntity.ok().body(reunion);
    }

    @Operation(summary = "Liste toutes les reunions par page", description = "Liste toutes les reunions par page")
    @GetMapping("/list-page")
    public ResponseEntity<List<ReunionDTO>> findAll(Pageable pageable) {
        Page<ReunionDTO> reunions = reunionService.findAll(pageable);
        HttpHeaders headers = cil.bf.activiteApp.utils.PaginationUtil.getHeaders(reunions);
        return ResponseEntity.ok().headers(headers).body(reunions.getContent());
    }

    @Operation(summary = "Liste toutes les reunions", description = "Liste toutes les reunions")
    @GetMapping("/list")
    public ResponseEntity<List<ReunionDTO>> findAll() {
        List<ReunionDTO> reunions = reunionService.findAll();
        return ResponseEntity.ok().body(reunions);
    }

    @Operation(summary = "Supprime une reunion via un ID", description = "Supprime une reunion via un ID")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reunionService.delete(id);
        return ResponseEntity
                .noContent()
                .build();
    }

    @Operation(summary = "Reprogrammer une reunion", description = "La reunion a recuperer doit etre annulee ou a reprogrammer et est modifiee et ramenee a l etat INITIE")
    @PutMapping("/reprogrammer")
    public ResponseEntity<ReunionDTO> reprogrammer(@RequestBody ReunionDTO reunionDTO) throws URISyntaxException {
        if (reunionDTO.getId() == null) {
            throw new UpdateElementException();
        }
        ReunionDTO response = reunionService.reprogrammer(reunionDTO);
        return ResponseEntity.ok().body(response);
    }

    @Operation(summary = "Valide une reunion", description = "La reunion est recuperer via son ID et est modifie via un statut renseigner")
    @PutMapping("/valider-reunion")
    public ResponseEntity<ReunionDTO> validerReunion(@RequestBody ReunionDTO request) {
        ReunionDTO reunion = reunionService.validerReunion(request);
        String subject = "INVITATION A UNE REUNION LE " + reunion.getDateReunion();
        String body = "Bonjour cher-e collaborateur (trice), <br />"
                + "vous êtes invités à participer à une rencontre ce <b>" + reunion.getDateReunion() + "</b> à partir de <b>" + reunion.getHeureDebut() + "</b> dans la salle de : <b>" + reunion.getSalleSession()
                + "</b><br />Ordre du jour : <b>" + reunion.getOrdreJour() + "</b><br /><br />Cordialement !";
        for (String e : reunion.getEmailsParticipants()) {
            if (mailService.isEmailValid(e)) {
                mailService.sendEmail(e, subject, body);
            }
        }
        return ResponseEntity.ok().body(reunion);
    }

    @Operation(summary = "Annule une reunion", description = "La reunion a recuperer doit etre valide et elle est retrouver via son ID et est modifie via un statut renseigner")
    @PutMapping("/{id}/annuler-reunion")
    public ResponseEntity<ReunionDTO> annulerReunion(@PathVariable(value = "id") Long id, @RequestParam("statut") String statut) {
        ReunionDTO reunion = reunionService.annulerReunion(id, statut);
        return ResponseEntity.ok().body(reunion);
    }

    @PutMapping("/{id}/cloturer")
    public ResponseEntity<ReunionDTO> cloturer(@PathVariable(value = "id") Long id, @RequestParam("file") MultipartFile file) throws URISyntaxException {

        if (id == null || file.isEmpty()) {
            throw new UpdateElementException();
        }
        ReunionDTO response = reunionService.cloturer(id, file);
        return ResponseEntity.ok().body(response);
    }
}
