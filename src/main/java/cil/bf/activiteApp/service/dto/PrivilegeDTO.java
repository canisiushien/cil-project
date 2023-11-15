package cil.bf.activiteApp.service.dto;

import lombok.Data;

@Data
public class PrivilegeDTO {

    private Long id;
    private String code;
    private String libelle;
    private String description;
}
