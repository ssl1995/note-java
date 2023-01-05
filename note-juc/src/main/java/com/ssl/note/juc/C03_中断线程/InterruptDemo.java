package com.ssl.note.juc.C03_中断线程;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Author: SongShengLin
 * @Date: 2023/01/04 17:34
 * @Describe:
 */
public class InterruptDemo {

    /**
     * t1.interrupt();
     * 1.线程活跃，中断标志位设置为true
     * 2.线程阻塞，抛出异常
     *  2.1 如果是sleep阻塞的，会将中断标志重置为false；需要再次.interrupt()，才能=true
     */
    @Test
    public void test2() {
        Thread t1 = new Thread(() -> {

            // 如果线程状态此时已经为true，别的线程调用isInterrupted会抛出异常
            // sleep：会重置线程装填为false,catch里可以再次设置为ture
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                // sleep：会重置线程装填为false,catch里可以再次设置为ture
                // Thread.currentThread().interrupt();
                e.printStackTrace();
            }

//            for (int i = 0; i < 300; i++) {
//                System.out.println("-------" + i);
//            }
            System.out.println("after t1.interrupt()--第2次---: " + Thread.currentThread().isInterrupted());


        }, "t1");
        t1.start();

        System.out.println("before t1.interrupt()----: " + t1.isInterrupted());
        //实例方法interrupt()仅仅是设置线程的中断状态位设置为true，不会停止线程
        t1.interrupt();
        //活动状态,t1线程还在执行中
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after t1.interrupt()--第1次---: " + t1.isInterrupted());
        //非活动状态,t1线程不在执行中，已经结束执行了。
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after t1.interrupt()--第3次---: " + t1.isInterrupted());
    }

    /**
     * 静态方法Thread.interrupted():1.返回当前现在状态 2.再重置为false
     */
    @Test
    public void test3() {
        System.out.println(Thread.currentThread().getName() + "---" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "---" + Thread.interrupted());
        System.out.println("111111");
        Thread.currentThread().interrupt();
        System.out.println("222222");
        System.out.println(Thread.currentThread().getName() + "---" + Thread.interrupted());
        // 静态方法Thread.interrupted():1.返回当前现在状态 2.再重置为false
        System.out.println(Thread.currentThread().getName() + "---" + Thread.interrupted());
    }
}
