package io.toughbox.auth.global.security;

import io.toughbox.auth.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT 인증 필터 - 모든 요청마다 Authorization 헤더에서 JWT를 읽어 인증 처리
 * Spring Security FilterChain에 등록 필요
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.startsWith("/actuator/prometheus") || path.startsWith("/actuator/health") || path.startsWith("/error")) {
            chain.doFilter(request, response);
            return;
        }

        String bearer = request.getHeader("Authorization");

        // 토큰 존재 & "Bearer "로 시작하는지 검사
        if (bearer != null && bearer.startsWith("Bearer ")) {
            String token = bearer.substring(7);
            // 토큰에서 username 파싱 및 유효성 검증
            String username = jwtUtil.extractUsername(token);

            // 사용자가 SecurityContext에 없는 경우만 처리 (이미 인증된 요청 skip)
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // 유효한 토큰이면 인증 객체 생성 및 컨텍스트에 등록
                if (jwtUtil.validateToken(token, userDetails)) {
                    Authentication auth = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }
        // 반드시 다음 필터 호출
        chain.doFilter(request, response);
    }
}