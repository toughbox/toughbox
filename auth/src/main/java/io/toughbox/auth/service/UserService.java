package io.toughbox.auth.service;

import io.toughbox.auth.domain.User;
import io.toughbox.auth.dto.LoginResponse;
import io.toughbox.auth.dto.UserResponse;
import io.toughbox.auth.repository.auth.AuthRepository;
import io.toughbox.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthRepository authRepository;
    private final EncryptService encryptService;
    private final JwtUtil jwtUtil;
    private final OtpService otpService;

    public User createUser(String userId, String password) {
        return authRepository.createUser(new User(userId, password));
    }

    public LoginResponse login(String userId, String password) {
        User user = authRepository.getUserByUserId(userId);

        if (ObjectUtils.isEmpty(user.getUserId()))
            return new LoginResponse();

        if (encryptService.matches(password, user.getPassword())) {
            //return otpService.renewOtp(userId);

            String accessToken = jwtUtil.generateToken(user.getUserId());
            String refreshToken = jwtUtil.generateRefreshToken(user.getUserId());
            UserResponse userResponse = new UserResponse(String.valueOf(user.getId()), user.getUserId());
            LoginResponse loginResponse = new LoginResponse(accessToken, refreshToken, userResponse);

            return loginResponse;
        }

        throw new RuntimeException("로그인 중 오류가 발생하였습니다.");
    }
}
