package com.note.ssl.note.redis.分布式锁.redis;

import com.note.ssl.note.redis.uitls.JedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * redis单机版
 * 实现分布式锁优化
 */
@RestController
public class GoodController {
    public static final String REDIS_LOCK_KEY = "redisLockPay";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${server.port}")
    private String serverPort;


    /**
     * Redis分布式锁（单机版）
     */
    @GetMapping("/buy_goods")
    public String buy_Goods() {
        // 模拟购物的业务
        String value = UUID.randomUUID() + Thread.currentThread().getName();

        try {
            // 分布式锁，set key value ex=同时设置过期时间保证删除的运行
            Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK_KEY, value, 30L, TimeUnit.SECONDS);

            if (Boolean.FALSE.equals(flag)) {
                return "抢夺锁失败，请下次尝试";
            }

            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);

            if (goodsNumber > 0) {
                int realNumber = goodsNumber - 1;
                stringRedisTemplate.opsForValue().set("goods:001", realNumber + "");
                System.out.println("你已经成功秒杀商品，此时还剩余：" + realNumber + "件" + "\t 服务器端口：" + serverPort);
                return "你已经成功秒杀商品，此时还剩余：" + realNumber + "件" + "\t 服务器端口：" + serverPort;
            } else {
                System.out.println("商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口：" + serverPort);
            }
            return "商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口：" + serverPort;
        } finally {
            // 如果出现异常，需要释放锁，释放锁判断是不是当前线程释放锁 = 使用lua脚本

            try (Jedis jedis = JedisUtils.getJedis()) {
                // 使用lua脚本判断是不是当前线程，保证删除的原子性
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] " +
                        "then " +
                        "return redis.call('del', KEYS[1]) " +
                        "else " +
                        "   return 0 " +
                        "end";
                // 执行lua脚本判断
                Object result = jedis.eval(script, Collections.singletonList(REDIS_LOCK_KEY), Collections.singletonList(value));
                if ("1".equals(result.toString())) {
                    System.out.println("------del REDIS_LOCK_KEY success");
                } else {
                    System.out.println("------del REDIS_LOCK_KEY error");
                }
            }

        }
    }
}



