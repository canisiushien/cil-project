package cil.bf.activiteApp.service.dto;

import cil.bf.activiteApp.domain.enums.StatutExercice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Created by Zak TEGUERA on 14/09/2023.
 * <teguera.zakaria@gmail.com>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExerciceDTO {
    private  Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int annee;
    private StatutExercice statut;
    private boolean actif = false;
}
