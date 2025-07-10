package io.toughbox.auth.domain;

import io.toughbox.auth.entity.otp.OtpEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Otp {

    private String userId;
    private String otpCode;

    public Otp(String userId, String otpCode) {
        this.userId = userId;
        this.otpCode = otpCode;
    }

    public OtpEntity toEntity() {
        return new OtpEntity(userId, otpCode);
    }
}
