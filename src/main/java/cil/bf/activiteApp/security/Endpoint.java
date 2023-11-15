/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.security;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Bean de correspondance api -> privileges
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Getter
@Setter
@ToString
public class Endpoint {

    private String path;
    private List<String> authorities;

    public Endpoint() {
    }
}
