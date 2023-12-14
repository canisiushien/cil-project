/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cil.bf.activiteApp.web;

import cil.bf.activiteApp.config.AuthenticationResponse;
import cil.bf.activiteApp.security.JwtAuthenticationManager;
import cil.bf.activiteApp.service.UtilisateurService;
import cil.bf.activiteApp.service.dto.UtilisateurDTO;
import cil.bf.activiteApp.utils.JwtUtil;
import cil.bf.activiteApp.web.vm.LoginVM;
import cil.bf.activiteApp.web.vm.PasswordModif;
import cil.bf.activiteApp.web.vm.ResetConnectPaswword;
import cil.bf.activiteApp.web.vm.ResetPaswword;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private UtilisateurService service;

    private JwtAuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    public UtilisateurResource(UtilisateurService service, JwtAuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

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
     * @return
     */
    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody LoginVM authRequest) {
        String jwt = jwtUtil.generateToken(authRequest.getLogin(), false);
        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + jwt)
                .body(new AuthenticationResponse(jwt));
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
