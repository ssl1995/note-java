package com.ssl.notebase.thread.C02_两个异步模型.有返回结果的异步模型;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @Author: SongShengLin
 * @Date: 2022/12/06 23:23
 * @Describe:
 */
public class FutureTaskTest {
    public static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> stringFutureTask = new FutureTask<>(() -> "业务线程执行完毕!");
        // 1.futureTask可以结合Thread或者线程池来获取异步结果
        new Thread(stringFutureTask).start();

        // 2.FutureTask结合execute使用
        executorService.execute(stringFutureTask);
        System.out.println(stringFutureTask.get());

        executorService.shutdown();
    }

}
