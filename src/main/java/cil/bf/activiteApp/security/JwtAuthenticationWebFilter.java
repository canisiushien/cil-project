/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cil.bf.activiteApp.security;

import org.springframework.security.web.server.authentication.AuthenticationWebFilter;

/**
 *
 * @author MARAH
 */
public class JwtAuthenticationWebFilter extends AuthenticationWebFilter {

    public JwtAuthenticationWebFilter(JwtAuthenticationManager authenticationManager, JwtServerAuthenticationConverter authenticationConverter) {
        super(authenticationManager);
        this.setServerAuthenticationConverter(authenticationConverter);
    }
}
