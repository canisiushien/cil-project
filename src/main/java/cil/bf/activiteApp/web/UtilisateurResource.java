/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.web;

import cil.bf.activiteApp.exception.AuthentificationException;
import cil.bf.activiteApp.service.UtilisateurService;
import cil.bf.activiteApp.service.dto.UtilisateurDTO;
import cil.bf.activiteApp.web.vm.JwtAuthenticationResponse;
import cil.bf.activiteApp.web.vm.LoginVM;
import cil.bf.activiteApp.web.vm.PasswordModif;
import cil.bf.activiteApp.web.vm.ResetConnectPaswword;
import cil.bf.activiteApp.web.vm.ResetPaswword;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Toutes ressources liées à l'utilisateur
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@RestController
@RequestMapping("/api/auth/utilisateurs")
public class UtilisateurResource {

    @Autowired
    private UtilisateurService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * creation d'un user
     *
     * @param user : json data du user
     * @param request
     * @return
     */
    @PostMapping("/new")
    public ResponseEntity<UtilisateurDTO> addNewUser(@RequestBody UtilisateurDTO user, HttpServletRequest request) {
        return new ResponseEntity<>(service.saveUser(user, request), HttpStatus.CREATED);
    }

    /**
     * Modifie juste des infos basic (nom, prenom, email, login, fonction) du
     * user
     *
     * @param id
     * @param utilisateurDTO
     * @return
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UtilisateurDTO> updateUser(
            @PathVariable final Long id,
            @RequestBody final UtilisateurDTO utilisateurDTO) {
        return new ResponseEntity<>(service.updateUser(id, utilisateurDTO), HttpStatus.CREATED);
    }

    /**
     * Modifie des infos du user y compris ses profiles
     *
     * @param id : id du user sujet de modification
     * @param utilisateurDTO : donnees json de modif
     * @return
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<UtilisateurDTO> updateUserWithProfils(
            @PathVariable final Long id,
            @RequestBody final UtilisateurDTO utilisateurDTO) {
        return new ResponseEntity<>(service.updateUserWithProfils(id, utilisateurDTO), HttpStatus.CREATED);
    }

    /**
     * api d'authentification
     *
     * @param authRequest
     * @param request
     * @return
     */
    @PostMapping("/signin")
    public ResponseEntity<?> getToken(@RequestBody LoginVM authRequest, HttpServletRequest request) {
        if (!service.isUserGood(authRequest)) {
            throw new AuthentificationException("Les informations d'authentification sont erronées!");
        }

        if (!service.isUserActif(authRequest)) {
            throw new AuthentificationException("Le compte " + authRequest.getLogin() + " n'est pas activé");
        }
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));

        System.err.println("after AUth ---------------------> " + authenticate);

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        System.err.println("rememberMe AUth ---------------------> " + request.getHeader("rememberMe"));

        boolean rememberMe = Boolean.parseBoolean(request.getHeader("rememberMe"));

        if (authenticate.isAuthenticated()) {
            String jwt = service.generateToken(authRequest.getLogin(), rememberMe);
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + jwt)
                    .body(new JwtAuthenticationResponse(jwt));
        } else {
            throw new AuthentificationException("Echec d'authentification !");
        }
    }

    /**
     * endpoint d'activation du compte utilisateur
     *
     * @param token
     * @return string
     */
    @GetMapping("/confirm")
    public ResponseEntity<String> processConfirmationForm(final @RequestParam("token") String token) {
        return new ResponseEntity<>(service.processConfirmationForm(token), HttpStatus.OK);

    }

    /**
     * endpoint d'activation du compte utilisateur
     *
     * @param passwordModif
     * @return string
     */
    @PostMapping("/confirm-user")
    public ResponseEntity<String> processAdminConfirm(final @RequestBody PasswordModif passwordModif) {
        return new ResponseEntity<>(service.processAdminConfirm(passwordModif), HttpStatus.OK);

    }

    /**
     *
     * @param request
     * @return
     */
    @GetMapping("/validate")
    public UtilisateurDTO validateToken(HttpServletRequest request) {
        service.validateToken(request);
        return service.validateToken(request);
    }

    /**
     * On renvoi un Lien (par mail) de confirmation/activation compte
     *
     * @param to
     * @param request
     * @return
     */
    @PostMapping("/reset-confirm-token")
    public ResponseEntity<String> resendConfirmToken(@RequestParam("to") String to, HttpServletRequest request) {
        return new ResponseEntity<>(service.resendConfirmToken(to, request), HttpStatus.CREATED);
    }

    /**
     * On renvoi un Lien (par mail) pour reinitialisation de password
     *
     * @param to
     * @param request
     * @return
     */
    @PostMapping("/reset-password-token")
    public ResponseEntity<String> resendPasswordToken(@RequestParam("to") String to, HttpServletRequest request) {
        return new ResponseEntity<>(service.resendPasswordToken(to, request), HttpStatus.CREATED);

    }

    /**
     * on reinitialise le mot de passe en verifiant le token generé par mail et
     * le nouveau password renseigné
     *
     * @param resetPaswword
     * @return
     */
    @PostMapping("/reset-password")
    public ResponseEntity<String> processResetPassword(final @RequestBody ResetPaswword resetPaswword) {
        return new ResponseEntity<>(service.processResetPassword(resetPaswword), HttpStatus.OK);
    }

    /**
     * On recueille l'ancien et le nouveau password et effectue le changement de
     * password
     *
     * @param resetPassword
     * @param request
     * @return
     */
    @PostMapping("/reset-connect-password")
    public ResponseEntity<String> processResetConnectPassword(final @RequestBody ResetConnectPaswword resetPassword, HttpServletRequest request) {
        return new ResponseEntity<>(service.processResetConnectPassword(resetPassword, request), HttpStatus.OK);
    }

    /**
     * On liste tous les utilisateurs avec leurs profils respectifs
     *
     * @return
     */
    @GetMapping(path = "/list")
    public ResponseEntity<List<UtilisateurDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }
}
