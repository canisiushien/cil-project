package cil.bf.activiteApp.service.dto;

import cil.bf.activiteApp.domain.enums.EtatMandatControle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MandatControleDTO {

    private  Long id;
    private  String libelle;
    private  String numSession;
    private  String numDossierDecla;
    private  String motif;
    private  LocalDate dateDebut;
    private  LocalDate dateFin;
    private  String typeMission;
    private EtatMandatControle etat;
    private  String piecesJointes;
}
