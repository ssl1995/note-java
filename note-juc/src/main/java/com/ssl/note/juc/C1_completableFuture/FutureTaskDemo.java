package com.ssl.note.juc.C1_completableFuture;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Author: SongShengLin
 * @Date: 2023/01/02 11:16
 * @Describe:
 */
public class FutureTaskDemo {

    /**
     * FutureTask1
     */
    @Test
    public void futureTask1() throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("-----come in FutureTask");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "" + ThreadLocalRandom.current().nextInt(100);
        });

        Thread t1 = new Thread(futureTask, "t1");
        t1.start();

        // futureTask.get():会阻塞 = 不见不散
        // System.out.println(Thread.currentThread().getName()+"\t"+futureTask.get());

        // futureTask.get(1L, TimeUnit.SECONDS):不到时间会抛出异常 = 过时不候
        System.out.println(Thread.currentThread().getName() + "\t" + futureTask.get(1L, TimeUnit.SECONDS));

        System.out.println(Thread.currentThread().getName() + "\t" + " run... here");
    }


    /**
     * FutureTask2
     */
    @Test
    public void futureTask2() throws ExecutionException, InterruptedException, TimeoutException {

        FutureTask futureTask = new FutureTask(() -> {
            System.out.println("-----come in FutureTask");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "" + ThreadLocalRandom.current().nextInt(100);
        });

        new Thread(futureTask, "t1").start();

        System.out.println(Thread.currentThread().getName() + "\t" + "线程完成任务");

        /**
         * futureTask.isDone()
         * 用于阻塞式获取结果,如果想要异步获取结果,通常都会以轮询的方式去获取结果
         */
        while (true) {
            if (futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            }
        }
    }


}
