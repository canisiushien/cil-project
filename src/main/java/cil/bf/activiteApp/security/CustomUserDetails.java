package cil.bf.activiteApp.security;

import cil.bf.activiteApp.domain.Utilisateur;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private boolean actif;

    public CustomUserDetails(Utilisateur userCredential) {
        this.username = userCredential.getLogin();
        this.password = userCredential.getPassword();
        this.actif = userCredential.isActif();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return actif;
    }

    @Override
    public boolean isAccountNonLocked() {
        return actif;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return actif;
    }

    @Override
    public boolean isEnabled() {
        return actif;
    }
}
