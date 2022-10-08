package com.note.ssl.note.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

@SpringBootTest
class NoteRedisApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void initTest() {
        String ping = Objects.requireNonNull(redisTemplate.getConnectionFactory()).getConnection().ping();
        System.out.println("ping = " + ping);
    }

    @Test
    void strTest() {
        redisTemplate.opsForValue().set("username","张三1");
        Object username = redisTemplate.opsForValue().get("username");
        System.out.println((String)username);
    }

}
