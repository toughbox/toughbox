package io.toughbox.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        //기본적으로 csrf는 활성화 상태(토큰기반 인증만 사용 시 비활성화)
        /*
        토큰 인증만 사용하는 경우 CSRF 비활성화가 권장되는 이유

        1. CSRF 공격의 원리
        **CSRF(Cross-Site Request Forgery)**는 사용자가 로그인된 상태(세션/쿠키 기반)에서, 악의적인 사이트가 사용자를 대신해 요청을 보내도록 유도하는 공격입니다.

        서버는 사용자의 세션 쿠키만 보고 요청을 신뢰하기 때문에, 공격자가 위조된 요청을 보낼 수 있습니다.

        2. 토큰 인증 방식의 특징
        **토큰 인증(JWT 등)**은 클라이언트가 매 요청마다 인증 토큰(Authorization 헤더 등)을 명시적으로 전송합니다.

        브라우저는 이 토큰을 자동으로 첨부하지 않으며, 오직 클라이언트(자바스크립트 등)가 직접 헤더에 넣어야 합니다.

        토큰이 없으면 서버가 요청을 거부하므로, 악의적인 사이트가 임의로 인증된 요청을 보내는 것이 불가능합니다.

        3. 왜 CSRF 비활성화가 권장되는가?
        CSRF 공격은 브라우저가 자동으로 쿠키를 첨부할 때만 효과적입니다.

        토큰 인증은 쿠키가 아니라 명시적 헤더로 인증 정보를 보내기 때문에,
        공격자가 임의로 인증 토큰을 획득하거나 전송하지 않는 한 CSRF 공격이 불가능합니다.

        REST API 등에서 토큰 인증만 사용한다면, CSRF 방어 로직이 불필요하게 리소스를 소모할 수 있으므로 비활성화하는 것이 일반적입니다.

        4. 실제 적용 예시
        Spring Security 등에서는 REST API 서버, 모바일 백엔드 등 토큰 인증만 사용하는 환경에서
        .csrf().disable() 설정을 권장합니다.
         */
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.authorizeHttpRequests(c -> c.anyRequest().permitAll());
        return httpSecurity.build();
    }
}
