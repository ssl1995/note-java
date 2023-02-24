package com.ssl.note.config.db.aop;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Author: SongShengLin
 * @Date: 2023/02/24 15:41
 * @Describe:
 */
public class DataSourceCache<K, V> {

    private Cache<? super K, ? super V> dataSourceCache = CacheBuilder.newBuilder()
            .expireAfterAccess(1000, TimeUnit.SECONDS)
            .expireAfterWrite(1000, TimeUnit.SECONDS)
            .maximumSize(100)
            .build();


    public void put(K key, V value) {
        dataSourceCache.put(key, value);
    }

    public static void main(String[] args) {
//        dataSourceCache.
    }

}
