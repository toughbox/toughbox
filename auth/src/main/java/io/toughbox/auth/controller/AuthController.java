package io.toughbox.auth.controller;

import io.toughbox.auth.controller.request.SimpleOtpRequestBody;
import io.toughbox.auth.controller.request.SimpleUserRequestBody;
import io.toughbox.auth.service.OtpService;
import io.toughbox.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public boolean check(@RequestBody SimpleOtpRequestBody simpleOtpRequestBody) {
        return otpService.checkOtp(simpleOtpRequestBody.getUserId(), simpleOtpRequestBody.getOtp());
    }
}
