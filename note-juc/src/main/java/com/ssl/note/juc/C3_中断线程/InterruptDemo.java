package com.ssl.note.juc.C3_中断线程;

import java.util.concurrent.TimeUnit;

/**
 * @Author: SongShengLin
 * @Date: 2023/01/04 17:34
 * @Describe:
 */
public class InterruptDemo {
//    public static void main(String[] args) {
//        Thread t1 = new Thread(() -> {
//            while (true) {
//                if (Thread.currentThread().isInterrupted()) {
//                    System.out.println("-----t1 线程被中断了，break，程序结束");
//                    break;
//                }
//                System.out.println("-----hello");
//            }
//        }, "t1");
//        t1.start();
//
//        System.out.println("**************" + t1.isInterrupted());
//        //暂停5毫秒
//        try {
//            TimeUnit.MILLISECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        t1.interrupt();
//        System.out.println("**************" + t1.isInterrupted());
//    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {

            // 如果线程状态此时已经为true，别的线程调用isInterrupted会抛出异常
            // sleep：会重置线程装填为false,catch里可以再次设置为ture
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
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
}
