package cil.bf.activiteApp.service.dto;

import cil.bf.activiteApp.domain.TypeDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDTO {

    private Long id;
    private String libelle;
    private String chemin;
    private String format;
    private TypeDocument typeDocument;

}
