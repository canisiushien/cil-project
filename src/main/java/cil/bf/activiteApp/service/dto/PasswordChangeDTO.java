/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.dto;

import lombok.Data;

/**
 * prevu pour permettre à l'utilisateur courant de changer lui-meme son password
 * etant connecté
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Data
public class PasswordChangeDTO {

    private String currentPassword;

    private String newPassword;
}
