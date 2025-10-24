package io.toughbox.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StressController {

    @RequestMapping("/stress/loop")
    public String loop() throws Exception {
        Long val = 0L;
        for (long i = 0L; i < 1000000000L; i++) {
            val += 1L;
        }

        return "Success" + val;
    }
}
