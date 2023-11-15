package cil.bf.activiteApp.service.dto;

import cil.bf.activiteApp.domain.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {

    private Long id;
    private String message;
    private String action;
    private boolean actif = false;
    private Long idAction;
    private Utilisateur utilisateur;
}
