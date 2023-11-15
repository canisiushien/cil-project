package cil.bf.activiteApp.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Component
public class JwtService {

    @Value("${jwt.base64-secret}")
    private String secret;

    @Value("${jwt.token-validity-in-seconds}")
    private long validityInMilliseconds;

    @Value("${jwt.token-validity-in-seconds-for-remember-me}")
    private long rememberMeValidityInMilliseconds;

    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }

    public String generateToken(String userName, boolean rememberMe) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName, rememberMe);
    }

    private String createToken(Map<String, Object> claims, String userName, boolean rememberMe) {
        Instant now = Instant.now();
        Instant validity;

        if (rememberMe) {
            validity = now.plusSeconds(rememberMeValidityInMilliseconds);
        } else {
            validity = now.plusSeconds(validityInMilliseconds);
        }
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(validity))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
