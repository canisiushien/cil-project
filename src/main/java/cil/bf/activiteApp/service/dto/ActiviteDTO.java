package cil.bf.activiteApp.service.dto;

import cil.bf.activiteApp.domain.Indicateur;
import cil.bf.activiteApp.domain.ObjectifOperationnel;
import cil.bf.activiteApp.domain.enums.StatutActivite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActiviteDTO {

    private  Long id;
    private  String code;
    private  String libelle;
    private StatutActivite statut;
    private  Double coutPrevisionnel;
    private  Double coutReel;
    private  String uniteReel;
    private  Double cibleReel;
    private  String responsable;
    private LocalDate dateDebut;
    private  LocalDate dateFin;
    private  String lieu;
    private ObjectifOperationnel objectifOperationnel;
    private Indicateur indicateur;
}
