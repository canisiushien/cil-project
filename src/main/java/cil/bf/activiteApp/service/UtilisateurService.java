/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service;

import cil.bf.activiteApp.service.dto.UtilisateurDTO;
import cil.bf.activiteApp.web.vm.LoginVM;
import cil.bf.activiteApp.web.vm.PasswordModif;
import cil.bf.activiteApp.web.vm.ResetConnectPaswword;
import cil.bf.activiteApp.web.vm.ResetPaswword;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface UtilisateurService {

    UtilisateurDTO saveUser(UtilisateurDTO utilisateurDTO, HttpServletRequest request);

    UtilisateurDTO updateUser(final Long id, final UtilisateurDTO utilisateurDTO);

    UtilisateurDTO updateUserWithProfils(final Long id, final UtilisateurDTO utilisateurDTO);

    Boolean isUserGood(LoginVM authRequest);

    Boolean isUserActif(LoginVM authRequest);

    String generateToken(String username, boolean rememberMe);

    String processConfirmationForm(final String token);

    String processAdminConfirm(final PasswordModif passwordModif);

    UtilisateurDTO validateToken(HttpServletRequest request);

    String resendConfirmToken(String to, HttpServletRequest request);

    String resendPasswordToken(final String to, HttpServletRequest request);

    String processResetPassword(final ResetPaswword resetPaswword);

    String processResetConnectPassword(final ResetConnectPaswword resetPaswword, HttpServletRequest request);

    List<UtilisateurDTO> findAll();
}
