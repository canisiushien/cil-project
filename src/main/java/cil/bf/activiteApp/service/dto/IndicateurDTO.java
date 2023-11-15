package cil.bf.activiteApp.service.dto;

import cil.bf.activiteApp.domain.Activite;
import cil.bf.activiteApp.domain.TypeIndicateur;
import cil.bf.activiteApp.domain.Unite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndicateurDTO {

    private  Long id;
    private  Long valeur;
    private  String libelle;
    private  String reference;
    private  Double cible;
    private TypeIndicateur typeIndicateur;
    private Unite unite;
   // private Activite activite;
}
