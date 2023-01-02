package com.note.ssl.note.redis.缓存击穿;


import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class JHSABTaskService {
    @Autowired
    private RedisTemplate redisTemplate;

    public static final String JSH_KEY_A = "JSH_KEY_A";
    public static final String JSH_KEY_B = "JSH_KEY_B";

    @PostConstruct
    public void initJHSAB() {
        log.info("启动AB定时器计划任务淘宝聚划算功能模拟.........." + DateUtil.now());
        new Thread(() -> {
            // 模拟定时器，定时把数据库的特价商品，刷新到redis中
            while (true) {
                // 新建：先更新B再更新A，严格按照这个顺序
                // 模拟从数据库读取100件特价商品，用于加载到聚划算的页面中
                List list = this.products();
                // 先更新B缓存
                this.redisTemplate.delete(JSH_KEY_B);
                // 数据结构：支持分页和排序，并且写死的，可用list这个结结构
                this.redisTemplate.opsForList().leftPushAll(JSH_KEY_B, list);
                // 过期时间设置差5天=差异化失效时间
                this.redisTemplate.expire(JSH_KEY_B, 20L, TimeUnit.DAYS);
                // 再更新A缓存
                this.redisTemplate.delete(JSH_KEY_A);
                this.redisTemplate.opsForList().leftPushAll(JSH_KEY_A, list);
                this.redisTemplate.expire(JSH_KEY_A, 15L, TimeUnit.DAYS);
                // 间隔一分钟 执行一遍
                try {
                    TimeUnit.MINUTES.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                log.info("runJhs定时刷新..............");
            }
        }, "t1").start();
    }

    /**
     * 模拟从数据库读取100件特价商品，用于加载到聚划算的页面中
     */
    public List products() {
        List list = new ArrayList();
        for (int i = 1; i < 20; i++) {
            Random rand = new Random();
            int id = rand.nextInt(10000);
            Product obj = new Product((long) id, "product" + i, i, "detail");
            list.add(obj);
        }
        return list;
    }
}