package org.zerobase.restaurantreservationproject.global.auth;

import jakarta.persistence.EntityManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.zerobase.restaurantreservationproject.domain.manager.dto.CustomManagerDetails;
import org.zerobase.restaurantreservationproject.domain.manager.entity.ManagerEntity;
import org.zerobase.restaurantreservationproject.domain.user.dto.CustomUserDetails;
import org.zerobase.restaurantreservationproject.domain.user.entity.UserEntity;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final EntityManager entityManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // request 에서 Authorization 헤더를 찾기
        String authorization = request.getHeader("Authorization");

        // Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            //조건이 해당되면 메소드 종료
            return;
        }

        // Bearer 부분 제거 후 순수 토큰만 획득
        String token = authorization.split(" ")[1];

        // 토큰 소멸 시간 검증
        if (jwtUtil.isExpired(token)) {
            filterChain.doFilter(request, response);
            // 조건이 해당되면 메소드 종료
            return;
        }

        // 토큰에서 값 획득
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        // ROLE_MANAGER / ROLE_USER 일때 각각 회원정보 객체에 담기
        UserDetails userDetails = null;
        if ("ROLE_MANAGER".equals(role)) {
            ManagerEntity managerEntity = entityManager.find(ManagerEntity.class, username);
            userDetails = new CustomManagerDetails(managerEntity);
        } else if ("ROLE_USER".equals(role)) {
            UserEntity userEntity = entityManager.find(UserEntity.class, username);
            userDetails = new CustomUserDetails(userEntity);
        }

        // 스프링 시큐리티 인증 토큰 생성
        if (userDetails != null) {
            Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
}
