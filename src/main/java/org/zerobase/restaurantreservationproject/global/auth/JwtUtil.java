package org.zerobase.restaurantreservationproject.global.auth;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private SecretKey secretKey;

    /**
     * secretKey 암호화
     */
    public JwtUtil(@Value("${spring.jwt.secret}") String secret) {
        secretKey = new SecretKeySpec(
                            secret.getBytes(StandardCharsets.UTF_8),
                            Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    /**
     * getUsername, getRole, isExpired 를 통해 검증
     */
    public String getUsername(String token) {
        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token).getPayload()
                .get("userName", String.class);
    }

    public String getRole(String token) {
        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token).getPayload()
                .get("role", String.class);
    }

    public Boolean isExpired(String token) {
        return Jwts.parser().verifyWith(secretKey).build()
                .parseSignedClaims(token).getPayload()
                .getExpiration().before(new Date());
    }

    /**
     * 토큰 생성
     */
    public String createJwt(String username, String role, Long expired) {
        return Jwts.builder()
                .claim("userName", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expired))
                .signWith(secretKey)
                .compact();
    }

}
