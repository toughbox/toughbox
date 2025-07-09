package io.toughbox.auth.repository.user;

import io.toughbox.auth.entity.user.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findUserEntityByUserId(String userId);
}
