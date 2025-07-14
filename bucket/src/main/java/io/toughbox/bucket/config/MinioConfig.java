package io.toughbox.bucket.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinioConfig {
    @Bean
    public MinioClient getMinIOConfig() {
        return MinioClient.builder()
                .endpoint("http://toughbox.iptime.org:9000")
                .credentials("tough", "12345678")
                .build();
    }
}
