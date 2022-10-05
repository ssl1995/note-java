package com.ssl.notebase.kafak.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

/**
 * @author SongShengLin
 * @date 2022/10/5 13:42
 * @description
 */
public class SamplePartition implements Partitioner {

    @Override
    public int partition(String s, Object key, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
         /*
            key-1
            key-2
            key-3
         */
        String keyStr = key + "";
        String keyInt = keyStr.substring(4);
        System.out.println("keyStr : " + keyStr + "，keyInt : " + keyInt);

        int i = Integer.parseInt(keyInt);
        // partition需要增加到2个，才会有效果，否则死循环
        return i % 2;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
