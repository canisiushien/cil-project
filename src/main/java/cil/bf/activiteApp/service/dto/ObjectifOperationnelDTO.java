package cil.bf.activiteApp.service.dto;

import cil.bf.activiteApp.domain.Action;
import cil.bf.activiteApp.domain.Indicateur;
import cil.bf.activiteApp.domain.ObjectifOperationnel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectifOperationnelDTO {

    private Long id;
    private String code;
    private String libelle;
    private Double cout;
    private Double coutReel;
    private Double cibleReel;
    private String uniteReel;
    private ObjectifOperationnel objectifOperationnelParent;
    private Action action;
    private Indicateur indicateur;
}
