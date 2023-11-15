package cil.bf.activiteApp.service.dto;

import cil.bf.activiteApp.domain.Indicateur;
import cil.bf.activiteApp.domain.Programme;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectifStrategiqueDTO {

    private  Long id;
    private  String code;
    private  String libelle;
    private  Double cout;
    private  Double coutReel;
    private  String uniteReel;
    private  Double cibleReel;
    private Programme programme;
    private Indicateur indicateur;
}
