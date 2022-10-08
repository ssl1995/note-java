package com.note.ssl.note.redis.jedis;

import com.note.ssl.note.redis.uitls.JedisPoolConnectRedis;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @Author: SongShengLin
 * @Date: 2022/10/08 16:58
 * @Describe:
 */
public class JedisTest {

    public static final String HOST = "101.201.154.144";
    public static final int PORT = 6379;
    public static final String AUTH_PASSWORD = "Ssl@134679";

    public static Jedis jedis = null;

    @Before
    public void init() {
//        jedis = new Jedis(HOST, PORT);
        jedis = JedisPoolConnectRedis.getJedis();

        jedis.auth(AUTH_PASSWORD);

        // 检测是否连接成功
        String pong = jedis.ping();
        // ping = PONG
        System.out.println("pong = " + pong);
    }


    @Test
    public void insertTest() {
        // 选库
        jedis.select(0);
        jedis.set("username", "李浩");
        System.out.println(jedis.get("username"));
        // 多层级设置
        jedis.set("imooc:username","张三");
        System.out.println(jedis.get("imooc:username"));

        jedis.flushDB();
        System.out.println(jedis.get("username"));

        jedis.select(1);
        jedis.set("username", "李浩");
        System.out.println(jedis.get("username"));

    }

    @After
    public void close() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
