package cil.bf.activiteApp.service.dto;

import cil.bf.activiteApp.domain.*;
import cil.bf.activiteApp.domain.enums.EtatReunion;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReunionDTO {

    private Long id;
    private LocalDate dateReunion;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String ordreJour;
    private String numeroReunion;
    private EtatReunion etat;
    private String intituleSession;
    private String salleSession;
    private Long idTypeReunion;
    private Long idTypeSession;
    private Long rapporteur;
    private Long president;
    private List<Long> membresReunion;
    private List<Document> documents;
    private List<String> emailsParticipants; //ajouter pour envoyer des mails groupés. Ceci n'est pas renseigné par le client
}
