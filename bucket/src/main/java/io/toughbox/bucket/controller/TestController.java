package io.toughbox.bucket.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity test() {
        return new ResponseEntity<>(
                Map.of(
                        "title: ", "Bucket service!!!"
                ), HttpStatus.OK
        );
    }
}
