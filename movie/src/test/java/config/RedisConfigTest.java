package config;

import io.github.tobi.laa.spring.boot.embedded.redis.standalone.EmbeddedRedisStandalone;
import io.toughbox.movie.MovieApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import static org.junit.jupiter.api.Assertions.*;

@EmbeddedRedisStandalone
@SpringBootTest(classes = MovieApplication.class)
public class RedisConfigTest {

    @Autowired
    StringRedisTemplate stringRedisTemplate; // 문자열 전용 직렬화로 바로 사용 가능

    @Test
    void setAndGet() {
        stringRedisTemplate.opsForValue().set("testkey", "Toughbox");
        String v = stringRedisTemplate.opsForValue().get("testkey");
        assertEquals("Toughbox", v); // [web:88]
    }
}