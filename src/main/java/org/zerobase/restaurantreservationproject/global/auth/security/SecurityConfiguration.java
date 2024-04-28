package org.zerobase.restaurantreservationproject.global.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.zerobase.restaurantreservationproject.global.role.PersonRole;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final AuthenticationConfiguration authenticationConfiguration;

    public SecurityConfiguration(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // csrf disable
        http.csrf((auth) -> auth.disable());

        // form 로그인 방식 disable
        http.formLogin((auth) -> auth.disable());

        // http basic 인증 방식 disable
        http.httpBasic((auth) -> auth.disable());

        // 경로별 인가 작업
        http.authorizeHttpRequests((auth) -> auth
                // TODO
                //  - 회원가입/로그인 페이지는 누구나 접근 가능
                .requestMatchers("/").permitAll()
                // manager 페이지는 점장님만 접근 가능
                .requestMatchers("/manager/**").hasAuthority(PersonRole.ROLE_MANAGER.toString()));

        // 필터 추가
        http.addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration)), UsernamePasswordAuthenticationFilter.class);

        // 세션 stateless 설정
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
