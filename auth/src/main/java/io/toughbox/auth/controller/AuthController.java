package io.toughbox.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.toughbox.auth.controller.request.SimpleOtpRequestBody;
import io.toughbox.auth.controller.request.SimpleUserRequestBody;
import io.toughbox.auth.service.OtpService;
import io.toughbox.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    public final OtpService otpService;
    public final UserService userService;

    @Operation(summary = "사용자 인증", description = "사용자 인증 후 otp 반환")
    @PostMapping("/users/auth")
    public String auth(@RequestBody SimpleUserRequestBody simpleUserRequestBody) {
        return userService.auth(simpleUserRequestBody.getUserId(), simpleUserRequestBody.getPassword());
    }

    @Operation(summary = "otp 검증", description = "유효한 otp인지 검사 (유효하면 true 반환)")
    @GetMapping("/otp/check")
    public boolean check(@RequestParam String userId,
                         @RequestParam String otp) {
        return otpService.checkOtp(userId, otp);
    }
}
