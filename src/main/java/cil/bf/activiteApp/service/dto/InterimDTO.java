package cil.bf.activiteApp.service.dto;

import cil.bf.activiteApp.domain.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterimDTO {

    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String motif;
    private Utilisateur interimaire;
    private Utilisateur responsable;
    private boolean actif = true;
}
