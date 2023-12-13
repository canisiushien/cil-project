/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cil.bf.activiteApp.config;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author MARAH
 */
@Setter
@Getter
@ToString
public class LoginVM implements Serializable {

    private String username;

    private String password;
}
