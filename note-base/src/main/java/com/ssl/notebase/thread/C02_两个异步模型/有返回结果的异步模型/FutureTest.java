package com.ssl.notebase.thread.C02_两个异步模型.有返回结果的异步模型;

import java.util.concurrent.*;

/**
 * @Author: SongShengLin
 * @Date: 2022/12/06 23:18
 * @Describe:
 */
public class FutureTest {

    public static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // executorService直接submit，返回的就是future
        Future<String> future = executorService.submit(() -> "业务线程执行完毕！");

        System.out.println(future.get());
        executorService.shutdown();
    }
}
