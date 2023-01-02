package com.ssl.note.juc.C1_completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @Author: SongShengLin
 * @Date: 2023/01/02 11:37
 * @Describe:
 */
public class CompletableFutureDemo {

    /**
     * CompletableFuture
     * Java8提供更灵活的API
     */
    public static void main(String[] args) throws Exception {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "-----come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-----计算结束耗时1秒钟，result： " + result);
            if (result > 6) {
                int age = 10 / 0;
            }
            return result;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("-----result: " + v);
            }
        }).exceptionally(e -> {
            System.out.println("-----exception: " + e.getCause() + "\t" + e.getMessage());
            return -44;
        });

        //主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:暂停3秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
