package io.toughbox.gateway.global.security;

import io.toughbox.gateway.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
public class JwtReactiveSecurityContextRepository implements ServerSecurityContextRepository {

    private final JwtUtil jwtUtil;
    private final ReactiveUserDetailsService userDetailsService;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            //인증 로직 체크
            String authToken = authHeader.substring(7);
            try {
                String username = jwtUtil.extractUsername(authToken);
                if (username != null && jwtUtil.validateToken(authToken)) {
                    return userDetailsService.findByUsername(username)
                            .map(userDetails ->
                                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()))
                            .map(SecurityContextImpl::new);
                }
            } catch (Exception e) {
                log.error("Authentication error", e);
            }
        }

        return Mono.empty();
    }
}
