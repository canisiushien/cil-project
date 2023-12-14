package cil.bf.activiteApp.utils;

import cil.bf.activiteApp.domain.Privilege;
import cil.bf.activiteApp.domain.Utilisateur;
import cil.bf.activiteApp.repository.UtilisateurRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.base64-secret}")
    private String secret;

    @Value("${jwt.token-validity-in-seconds}")
    private long validityInMilliseconds;

    @Value("${jwt.token-validity-in-seconds-for-remember-me}")
    private long rememberMeValidityInMilliseconds;
    //public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username, boolean rememberMe) {
        Map<String, Object> claims = new HashMap<>();
        Utilisateur utilisateur = utilisateurRepository.findOneByLogin(username).orElse(null);
        Set<Privilege> privileges = utilisateur.getProfils().stream()
                .flatMap(profil -> profil.getPrivilegeCollection().stream())
                .collect(Collectors.toSet());
        List<String> privilegeNames = privileges.stream()
                .map(Privilege::getCode)
                .collect(Collectors.toList());
        claims.put("roles", privilegeNames);
        return createToken(claims, username, rememberMe);
    }

    public List<String> extractPrivileges(String token) {
        return (List<String>) extractAllClaims(token).get("roles");
    }

    private String createToken(Map<String, Object> claims, String subject, boolean rememberMe) {
        Instant now = Instant.now();
        Instant validity;

        if (rememberMe) {
            validity = now.plusSeconds(rememberMeValidityInMilliseconds);
        } else {
            validity = now.plusSeconds(validityInMilliseconds);
        }
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(validity))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        return token;
    }

    public Boolean validateToken(String token) {
        return (!isTokenExpired(token));
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
