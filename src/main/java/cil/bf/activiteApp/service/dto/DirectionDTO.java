/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.dto;

import lombok.Data;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Data
public class DirectionDTO {

    private Long id;

    private String libelle;

    private String sigle;

    private String responsable;
}
