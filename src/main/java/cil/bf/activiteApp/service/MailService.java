/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface MailService {

    void sendEmail(String to, String subject, String body);

    boolean isEmailValid(String email);
}
