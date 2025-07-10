package io.toughbox.auth.repository.auth;

import io.toughbox.auth.domain.Otp;
import io.toughbox.auth.domain.User;
import io.toughbox.auth.entity.user.UserEntity;
import io.toughbox.auth.repository.otp.OtpJpaRepository;
import io.toughbox.auth.repository.user.UserJpaRepository;
import io.toughbox.auth.service.EncryptService;
import io.toughbox.auth.service.OtpService;
import io.toughbox.auth.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionOperations;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AuthRepositoryTest {

    private String userId = "lhs";
    private String password = "1234";
    private String encryptedPassword = "$2a$10$kyLpR6CU9TjcEvL/fIYBruZK8keiUh2rjrDdYnDEL7Q5IjaqBLUmu";

    @Mock
    OtpJpaRepository otpJpaRepository;

    @Mock
    UserJpaRepository userJpaRepository;

    @Mock
    AuthRepository authRepository;

    @InjectMocks
    UserService userService;

    @InjectMocks
    OtpService otpService;

    EncryptService encryptService;

    @BeforeEach
    public void setup() {
        authRepository = new AuthRepository(userJpaRepository,
                otpJpaRepository,
                TransactionOperations.withoutTransaction(),
                TransactionOperations.withoutTransaction());
    }

    @Test
    @DisplayName("동일한 사용자 id 로 등록할 수 없다.")
    public void test1() {
        //given
        UserEntity userEntity = new UserEntity(userId, password);
        given(userJpaRepository.findUserEntityByUserId(userId))
                .willReturn(Optional.of(userEntity));

        //when & then
        Assertions.assertThrows(RuntimeException.class, () -> {
            authRepository.createUser(userEntity.toDomain());
        });
    }

    @Test
    @DisplayName("사용자를 등록할 수 있다.")
    public void test2() {
        //given
        given(userJpaRepository.findUserEntityByUserId(userId))
                .willReturn(Optional.empty());

        User user = new User(userId, password);
        given(userJpaRepository.save(any()))
                .willReturn(user.toEntity());

        //when
        User result = authRepository.createUser(user);

        //then
        verify(userJpaRepository, atMostOnce()).save(user.toEntity());
        Assertions.assertEquals(result.getUserId(), userId);
        Assertions.assertEquals(result.getPassword(), password);
    }
}