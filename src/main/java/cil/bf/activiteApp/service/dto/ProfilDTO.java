package cil.bf.activiteApp.service.dto;

import java.util.Set;
import lombok.Data;

@Data
public class ProfilDTO {

    private Long id;
    private String code;
    private String libelle;
    private String description;
    private boolean nativeProfile = false;
    private Set<PrivilegeDTO> privilegeCollection;
}
