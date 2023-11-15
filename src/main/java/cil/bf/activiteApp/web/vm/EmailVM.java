/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.web.vm;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * prevu pour la réinitialisation des passwords
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Data
public class EmailVM {

    @Email(message = "La valeur saisie ne ressemble pas à un email !")
    @NotBlank(message = "La valeur saisie ne ressemble pas à un email !")
    private String email;
}
