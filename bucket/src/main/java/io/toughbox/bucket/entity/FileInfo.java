package io.toughbox.bucket.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "file_info", uniqueConstraints = @UniqueConstraint(columnNames = "uuid"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 36, nullable = false)
    private String uuid;

    @Column(name = "origin_name", nullable = false)
    private String originName;

    @Column(length = 20)
    private String ext;

    private Long size;

    @Column(name = "content_type", length = 100)
    private String contentType;

    @Column(length = 100)
    private String bucket;

    @Column(length = 500)
    private String path;

    private Long uploaderId;

    @Column(length = 20)
    @Builder.Default
    private String status = "ACTIVE";

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
