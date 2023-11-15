package cil.bf.activiteApp.service.dto;

import cil.bf.activiteApp.domain.ObjectifStrategique;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionDTO {

    private Long id;
    private String code;
    private String libelle;
    private Double cout;
    private Double cibleReel;
    private String uniteReel;
    private ObjectifStrategique objectifStrategique;
}
