package io.toughbox.auth.controller;

import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ActuatorController {

    private final PrometheusMeterRegistry prometheusMeterRegistry;

    @GetMapping("/actuator/prometheus")
    public String scrape() {
        return prometheusMeterRegistry.scrape();
    }
}