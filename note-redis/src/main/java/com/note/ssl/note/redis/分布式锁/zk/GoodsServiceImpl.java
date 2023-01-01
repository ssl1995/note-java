package com.note.ssl.note.redis.分布式锁.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @Author: SongShengLin
 * @Date: 2023/01/01 14:05
 * @Describe: Zookeeper实现分布式锁
 */
@Service
@Transactional
public class GoodsServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

//    @Autowired
//    private ShopGoodsMapper shopGoodsMapper  ;
//
//    @Autowired
//    private shopGoodsUniqueMapper shopGoodsUniqueMapper;

    //Zookeeper分布式锁
    @Autowired
    private CuratorFramework curatorFramework;
    static InterProcessLock lock;

    public int updateGoods(long orderId, long goodsId, int goodsNumber) {
        synchronized (this) {
            if (lock == null) { //多线程安全问题
                lock = new InterProcessMutex(curatorFramework, "/locks2");
            }
        }
        int ireturn = -1;
        try {
            // zookeeper实现的分布式锁（临时、序号节点，包括监听机制）
            // 加锁
            if (lock.acquire(5, TimeUnit.SECONDS)) {
//                ShopGoods shopGoods = shopGoodsMapper.selectByPrimaryKey(goodsId);
//                Integer goodnumber = shopGoods.getGoodsNumber() - goodsNumber;
//                shopGoods.setGoodsNumber(goodnumber);
                int res = 1;
                if (res >= 0) {
                    //logger.info("修改库存成功：[" + orderId + "]");
                    ireturn = 1;
                } else {
                    logger.error("修改库存失败：[" + orderId + "]");
                    ireturn = -1;
                }
            } else {
                logger.error("修改库存失败：[拿不到zk分布式锁]");
                ireturn = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 解锁
            try {
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ireturn;
        }
    }


}
