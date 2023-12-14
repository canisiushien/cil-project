package cil.bf.activiteApp.security;

import cil.bf.activiteApp.domain.Utilisateur;
import cil.bf.activiteApp.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UtilisateurRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Utilisateur user = usersRepository.findOneByLogin(s).orElse(null);
        CustomUserDetails userPrincipal = new CustomUserDetails(user);
        return userPrincipal;
    }
}
