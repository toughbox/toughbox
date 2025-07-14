package io.toughbox.bucket.repository;

import io.toughbox.bucket.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
    Optional<FileInfo> findByUuid(String uuid);
}
