package io.toughbox.auth.repository.otp;

import io.toughbox.auth.entity.otp.OtpEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpJpaRepository extends JpaRepository<OtpEntity, Integer> {
    Optional<OtpEntity> findOtpEntityByUserId(String userId);
}
