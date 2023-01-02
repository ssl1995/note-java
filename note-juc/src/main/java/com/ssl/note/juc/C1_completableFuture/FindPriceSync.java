package com.ssl.note.juc.C1_completableFuture;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author: SongShengLin
 * @Date: 2023/01/02 11:26
 * @Describe:
 */
public class FindPriceSync {

    /**
     * 比较价格使用
     * CompletableFuture优化同步结果
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<String> list1 = findPriceSync(list, "thinking in java");
        for (String element : list1) {
            System.out.println(element);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime - startTime) + " 毫秒");

        long startTime2 = System.currentTimeMillis();
        List<String> list2 = findPriceASync(list, "thinking in java");
        for (String element : list2) {
            System.out.println(element);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("----costTime: " + (endTime2 - startTime2) + " 毫秒");
        /**
         * thinking in java jd price is 116.02
         * thinking in java tmall price is 116.44
         * thinking in java pdd price is 116.45
         * thinking in java mi price is 116.65
         * ----costTime: 4068 毫秒
         * thinking in java jd price is 116.78
         * thinking in java tmall price is 116.24
         * thinking in java pdd price is 116.30
         * thinking in java mi price is 116.68
         * ----costTime: 1021 毫秒
         */
    }

    static List<NetMall> list = Arrays.asList(
            new NetMall("jd"),
            new NetMall("tmall"),
            new NetMall("pdd"),
            new NetMall("mi")
    );

    /**
     * 同步获取价格
     */
    public static List<String> findPriceSync(List<NetMall> list, String productName) {
        return list.stream()
                .map(mall -> String.format(productName + " %s price is %.2f", mall.getNetMallName(), mall.getPriceByName(productName)))
                .collect(Collectors.toList());
    }

    /**
     * 异步获取价格
     */
    public static List<String> findPriceASync(List<NetMall> list, String productName) {
        return list.stream()
                // CompletableFuture.supplyAsync
                .map(mall -> CompletableFuture.supplyAsync(() -> String.format(productName + " %s price is %.2f", mall.getNetMallName(), mall.getPriceByName(productName))))
                .collect(Collectors.toList())
                .stream()
                // CompletableFuture:join
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}


class NetMall {
    @Getter
    private String netMallName;

    public NetMall(String netMallName) {
        this.netMallName = netMallName;
    }

    public double getPriceByName(String productName) {
        return calcPrice(productName);
    }

    private double calcPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble() + productName.charAt(0);
    }


}
