/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cil.bf.activiteApp.service.impl;

import cil.bf.activiteApp.domain.Direction;
import cil.bf.activiteApp.domain.Privilege;
import cil.bf.activiteApp.domain.Profil;
import cil.bf.activiteApp.domain.Utilisateur;
import cil.bf.activiteApp.repository.DirectionRepository;
import cil.bf.activiteApp.repository.PrivilegeRepository;
import cil.bf.activiteApp.repository.ProfilRepository;
import cil.bf.activiteApp.repository.UtilisateurRepository;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service d'auto initialisation de données
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Slf4j
@Service
public class InitalDataServiceImpl {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ProfilRepository profileRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private DirectionRepository directionRepository;

    @Value("${application.resources.static-locations}")
    private String dataPath;

    @Transactional
    public void saveInitAuthorities() throws FileNotFoundException, IOException {
        if (privilegeRepository.findAll().isEmpty()) {
            log.info("Chargement des données privileges... ok");
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("data/authorities.txt").getFile());
            try (BufferedReader lineReader = new BufferedReader(new FileReader(file))) {
                String lineText;
                int count = 0;
                List<Privilege> roles = new ArrayList<>();
                while ((lineText = lineReader.readLine()) != null) {
                    String[] data = lineText.split(";");
                    if (count != 0) {
                        Privilege role = new Privilege();
                        role.setId(Long.valueOf(data[0]));
                        role.setCode(data[1]);
                        role.setLibelle(data[2]);
                        roles.add(role);
                    }
                    count++;
                }
                privilegeRepository.saveAll(roles);
            }
        }
    }

    @Transactional
    public void saveInitProfil() {
        if (profileRepository.findAll().isEmpty()) {
            log.info("Chargement des données profiles... ok");
            Set<Privilege> privileges = privilegeRepository.findAll().stream().collect(Collectors.toSet());
            privileges.addAll(privileges);
            Profil profil = new Profil();
            profil.setId(1L);
            profil.setCode("ADMIN");
            profil.setLibelle("Administrateur");
            profil.setNativeProfile(true);
            profil.setPrivilegeCollection(privileges);
            profileRepository.save(profil);
        }
    }

    @Transactional
    public void saveInitUser() {
        if (utilisateurRepository.findAll().isEmpty()) {
            log.info("Chargement des données utilisateur... ok");
            Profil profile = profileRepository.findByCode("ADMIN").orElse(null);
            Direction direction = directionRepository.findBySigle("CIL").get();
            Utilisateur user = new Utilisateur();
            user.setId(1L);
            user.setLogin("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setNom("admin");
            user.setPrenom("admin");
            user.setMatricule("admin");
            user.setEmail("infos@cil.bf");
            user.setDirection(direction);
            user.setActif(true);
            user.setProfils(Arrays.asList(profile).stream().collect(Collectors.toSet()));
            utilisateurRepository.save(user);
            log.info("Utilisateur {} crée avec profile {}... ok", user.getNom(), profile.getCode());
        }
    }

    public void saveInitDirection() {
        if (directionRepository.findAll().isEmpty()) {
            log.info("Chargement des données direction... ok");
            Direction direction = new Direction();
            direction.setId(1L);
            direction.setSigle("CIL");
            direction.setLibelle("Commission de l'Informatique et des Libertés");
            direction.setResponsable("infos@cil.bf");
            directionRepository.save(direction);
        }
    }

}
