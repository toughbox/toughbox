package io.toughbox.gateway.controller;

import io.jsonwebtoken.Jwt;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    /*private final ReactiveAuthenticationManager authenticationManager;
    private final JwtUtil  jwtUtil;

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest authRequest) {

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
        // 로그인이 되었는지 검사
        return authenticationManager.authenticate(authentication)
                .map(auth -> {
                    String token = jwtUtil.generateToken(auth.getName());
                    return ResponseEntity.ok(new AuthResponse(token));
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @Data
    public static class AuthRequest {
        private String username;
        private String password;
    }

    public record AuthResponse(String token) {
    }*/
}
