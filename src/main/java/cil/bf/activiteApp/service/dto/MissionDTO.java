package cil.bf.activiteApp.service.dto;

import cil.bf.activiteApp.domain.enums.EtatMission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissionDTO {

    private  Long id;
    private  String numMission;
    private  Double budget;
    private  LocalDate dateDebut;
    private  LocalDate dateFin;
    private  String lieu;
    private EtatMission etat;
    private  String pieceJointe;
}
