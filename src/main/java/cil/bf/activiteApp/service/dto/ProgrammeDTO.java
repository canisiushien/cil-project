package cil.bf.activiteApp.service.dto;

import cil.bf.activiteApp.domain.Exercice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProgrammeDTO {

    private  Long id;
    private  String code;
    private  String libelle;
    private  Double cout;
    private  Double coutReel;
    private  String uniteReel;
    private  Double cibleReel;
    private Exercice exercice;
}
