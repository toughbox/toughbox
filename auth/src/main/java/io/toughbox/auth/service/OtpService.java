package io.toughbox.auth.service;

import io.toughbox.auth.repository.auth.AuthRepository;
import io.toughbox.auth.util.OtpCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final AuthRepository authRepository;

    public boolean checkOtp(String userId, String otp) {
        String targetOtp = authRepository.getOtp(userId);
        return targetOtp.equals(otp);
    }

    public String renewOtp(String userId) {
        String newOtp = OtpCodeUtil.generateOtpCode();
        authRepository.upsertOtp(userId, newOtp);
        return newOtp;
    }
}
