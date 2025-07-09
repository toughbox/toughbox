package io.toughbox.auth.controller;

import io.toughbox.auth.controller.request.SimpleOtpRequestBody;
import io.toughbox.auth.controller.request.SimpleUserRequestBody;
import io.toughbox.auth.service.OtpService;
import io.toughbox.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthController {

    public final OtpService otpService;
    public final UserService userService;

    @PostMapping("/api/v1/users/auth")
    public String auth(@RequestBody SimpleUserRequestBody simpleUserRequestBody) {
        return userService.auth(simpleUserRequestBody.getUserId(), simpleUserRequestBody.getPassword());
    }

    @GetMapping("/api/v1/otp/check")
    public boolean check(@RequestParam String userId,
                         @RequestParam String otp) {
        return otpService.checkOtp(userId, otp);
    }
}
