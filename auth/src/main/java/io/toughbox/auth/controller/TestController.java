package io.toughbox.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity test() {
        return new ResponseEntity<>(
                Map.of(
                        "title: ", "Auth service"
                ), HttpStatus.OK
        );
    }
}
