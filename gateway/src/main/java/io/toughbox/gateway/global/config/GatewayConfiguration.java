package io.toughbox.gateway.global.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    /*@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r .path("/fastcampus/**")
                        .filters(f -> f.rewritePath("/fastcampus/(?<segment>.*)", "/.api/www/${segment}"))

                        .uri("https://fastcampus.co.kr")
                ).build();
    }*/

    /*@Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-route", r -> r.path("/auth/**")
                        .filters(f -> f.addResponseHeader("Access-Control-Allow-Origin", "*")
                                .addResponseHeader("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS")
                                .addResponseHeader("Access-Control-Allow-Headers", "*"))
                        .uri("http://localhost:8080"))
                .build();
    }*/
}
