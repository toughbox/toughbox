package io.toughbox.auth.repository.auth;

import io.toughbox.auth.domain.User;
import io.toughbox.auth.entity.otp.OtpEntity;
import io.toughbox.auth.entity.user.UserEntity;
import io.toughbox.auth.exception.InvalidAuthException;
import io.toughbox.auth.repository.otp.OtpJpaRepository;
import io.toughbox.auth.repository.user.UserJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.support.TransactionOperations;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthRepository {
    private final UserJpaRepository userJpaRepository;
    private final OtpJpaRepository otpJpaRepository;

    private final TransactionOperations readTransactionOperations;
    private final TransactionOperations writeTransactionOperations;

    public User createUser(User user) {
        return writeTransactionOperations.execute(status -> {
            Optional<UserEntity> userOptional = userJpaRepository.findUserEntityByUserId(user.getUserId());

            if(userOptional.isPresent()) {
                throw new RuntimeException(String.format("User [%s] already exists", user.getUserId()));
            }

            UserEntity savedUserEntity = userJpaRepository.save(user.toEntity());
            return savedUserEntity.toDomain();
        });
    }

    public User getUserByUserId(String userId) {
        return readTransactionOperations.execute(status -> {
            return userJpaRepository.findUserEntityByUserId(userId)
                    //.orElseThrow(InvalidAuthException::new)
                    .orElse(new UserEntity())
                    .toDomain();

        });
    }

    public String getOtp(String userId) {
        return readTransactionOperations.execute(status -> {
           return otpJpaRepository.findOtpEntityByUserId(userId)
                   .orElseThrow(() -> new RuntimeException(String.format("User [%s] does not exist", userId)))
                   .getOtpCode();
        });
    }

    public void upsertOtp(String userId, String otpCode) {
        writeTransactionOperations.executeWithoutResult(status -> {
           Optional<OtpEntity> otpOptional = otpJpaRepository.findOtpEntityByUserId(userId);
           if (otpOptional.isPresent()) {
               otpOptional.get().renewOtp(otpCode);
           } else {
              otpJpaRepository.save(new OtpEntity(userId, otpCode));
           }
        });
    }
}
