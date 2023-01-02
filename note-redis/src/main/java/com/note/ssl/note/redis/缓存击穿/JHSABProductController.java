package com.note.ssl.note.redis.缓存击穿;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Slf4j
public class JHSABProductController {
    @Autowired
    private RedisTemplate redisTemplate;

    public static final String JSH_KEY_A = "JSH_KEY_A";
    public static final String JSH_KEY_B = "JSH_KEY_B";

    @RequestMapping(value = "/pruduct/findab", method = RequestMethod.GET)
    public List findAB(int page, int size) {
        List list = null;
        long start = (page - 1) * size;
        long end = start + size - 1;
        try {
            // 查询：先查A再查B
            //  采用redis list数据结构的lrange命令实现分页查询
            list = this.redisTemplate.opsForList().range(JSH_KEY_A, start, end);
            if (CollectionUtils.isEmpty(list)) {
                log.info("=========A缓存已经失效了，记得人工修补，B缓存自动延续5天");
            //  用户先查询缓存A(上面的代码)，如果缓存A查询不到（例如，更新缓存的时候删除了），再查询缓存B
                this.redisTemplate.opsForList().range(JSH_KEY_B, start, end);
            }
            log.info("查询结果：{}", list);
        } catch (Exception ex) {
            //这里的异常，一般是redis瘫痪 ，或 redis网络timeout
            log.error("exception:", ex);
//TODO 走DB查询
        }
        return list;
    }

}

