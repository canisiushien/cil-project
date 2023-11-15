/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * les données requises pour l'activation de compte
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Data
public class ActivatedPassword {

    @NotBlank
    @NotNull
    @NotEmpty
    private String key;

    @NotBlank
    @NotNull
    @NotEmpty
    private String password;

    public ActivatedPassword() {
    }

    public ActivatedPassword(@NotBlank @NotNull @NotEmpty String password, @NotBlank @NotNull @NotEmpty String key) {
        this.password = password;
        this.key = key;
    }
}
