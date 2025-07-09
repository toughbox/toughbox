package io.toughbox.auth.service;

import io.toughbox.auth.domain.User;
import io.toughbox.auth.repository.auth.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthRepository authRepository;

    public User createUser(String userId, String password) {
        return authRepository.createUser(new User(userId, password));
    }
}
