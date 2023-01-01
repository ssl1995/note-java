package com.note.ssl.note.redis.config.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: SongShengLin
 * @Date: 2023/01/01  13:56
 * @Describe: Zookeeper客户端配置
 */
@Configuration
public class CuratorConfig {

    @Autowired
    private WrapperZK wrapperZK;

    //这里返回一个zk的客户端的bean
    @Bean
    public CuratorFramework curatorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(wrapperZK.getElapsedTimeMs(), wrapperZK.getRetryCount());
        CuratorFramework client = CuratorFrameworkFactory.newClient(wrapperZK.getConnectString(),
                wrapperZK.getSessionTimeoutMs(),
                wrapperZK.getConnectionTimeoutMs(),
                retryPolicy);
        client.start();
        return client;
    }
}
