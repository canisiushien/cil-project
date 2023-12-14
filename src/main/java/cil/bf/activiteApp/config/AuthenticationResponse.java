/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cil.bf.activiteApp.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author MARAH
 */
@Getter
@Setter
@ToString
public class AuthenticationResponse {

    private String accessToken;
    private String tokenType = "Bearer";

    public AuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
