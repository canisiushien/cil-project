package cil.bf.activiteApp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeReunionDTO {

    private  Long id;
    private  String libelle;
    private  String description;
    private  String etat;
    private  String code;
    private boolean deleted;
}
