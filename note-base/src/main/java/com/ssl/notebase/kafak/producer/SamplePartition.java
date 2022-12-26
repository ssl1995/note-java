package com.ssl.notebase.kafak.producer;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

/**
 * @author SongShengLin
 * @date 2022/10/5 13:42
 * @description 自定义负载均衡器
 */
public class SamplePartition implements Partitioner {

    public static void main(String[] args) {
        System.out.println("keyStr-1".indexOf("-"));
        System.out.println("keyStr-1".substring("keyStr-1".indexOf("-") + 1));

    }

    @Override
    public int partition(String topic, Object key, byte[] bytes, Object o1, byte[] bytes1, Cluster cluster) {
        // 使用value作为负载均衡器标识
//        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
//        int num = partitionInfos.size();
//        int parId = Utils.toPositive(Utils.murmur2(bytes1)) % num;//来自DefaultPartitioner的处理
//        return parId;

        // 这里以key作为负载均衡器标识
        String keyStr = key + "";
        // 以一个-作为负载均衡的标识符
        int index = keyStr.indexOf("-");
        String keyInt = keyStr.substring(index + 1);
        int i = Integer.parseInt(keyInt);

        // 默认partition调到4个
        List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        int size = partitionInfos.size();
        System.out.println("生成者key: " + keyStr + "，标识符i : " + keyInt + "，i%+" + size + ":" + i % size);
        return i % size;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
