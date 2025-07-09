package io.toughbox.auth.service;

import io.toughbox.auth.domain.User;
import io.toughbox.auth.repository.auth.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthRepository authRepository;
    private final EncryptService encryptService;
    private final OtpService otpService;

    public User createUser(String userId, String password) {
        return authRepository.createUser(new User(userId, password));
    }

    public String auth(String userId, String password) {
        User user = authRepository.getUserByUserId(userId);
        if (encryptService.matches(password, user.getPassword())) {
            return otpService.renewOtp(userId);
        }

        throw new RuntimeException("Invalid password");
    }
}
