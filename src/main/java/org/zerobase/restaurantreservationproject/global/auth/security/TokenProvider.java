package org.zerobase.restaurantreservationproject.global.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.zerobase.restaurantreservationproject.global.auth.service.AuthService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    private static final String KEY_ROLES = "roles";
    private static final long TOKEN_EXPIRE_TIME = 1000 * 60 * 60;

    private final AuthService authService;

    @Value("${spring.jwt.secret")
    private String secretKey;

    /**
     * 토큰 생성
     */
    public String generateToken(String username, List<String> roles) {
        return Jwts.builder().compact();
    }

    /**
     * 토큰 유효성 검사
     */
    public boolean validateToken(String token) {
        return ;
    }

    /**
     * 토큰 기반 Authentication 구현체 생성
     */
    public Authentication getAuthentication(String token) {
        return ;
    }

    /**
     * Claims 에서 username 추출
     */
    private String getUserName(String token) {
        return ;
    }

    /**
     * 토큰에서 Claims 추출
     */
    private Claims getClaims(String token) {
        return ;
    }
}
