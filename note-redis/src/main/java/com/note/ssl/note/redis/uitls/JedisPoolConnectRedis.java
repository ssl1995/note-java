package com.note.ssl.note.redis.uitls;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @Author: SongShengLin
 * @Date: 2022/10/08 17:13
 * @Describe:
 */
public class JedisPoolConnectRedis {
    public static JedisPool jedisPool;

    public static final String HOST = "101.201.154.144";
    public static final int PORT = 6379;
    public static final String AUTH_PASSWORD = "Ssl@134679";

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setMinIdle(5);
        config.setMaxWait(Duration.ofMillis(100));
        jedisPool = new JedisPool(config, HOST, PORT, 100, AUTH_PASSWORD);
    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }
}
