package com.ssl.note.juc.C02_锁;

import java.util.concurrent.TimeUnit;

/**
 * @Author: SongShengLin
 * @Date: 2023/01/04 08:25
 * @Describe:
 */
public class DeadLockDemo {

    /**
     * 手写一个死锁
     */
    public static void main(String[] args) {
        final Object objectLockA = new Object();
        final Object objectLockB = new Object();

        new Thread(() -> {
            synchronized (objectLockA) {
                System.out.println(Thread.currentThread().getName() + "\t" + "自己持有A，希望获得B");
                //暂停几秒钟线程
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (objectLockB) {
                    System.out.println(Thread.currentThread().getName() + "\t" + "A-------已经获得B");
                }
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (objectLockB) {
                System.out.println(Thread.currentThread().getName() + "\t" + "自己持有B，希望获得A");
                //暂停几秒钟线程
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (objectLockA) {
                    System.out.println(Thread.currentThread().getName() + "\t" + "B-------已经获得A");
                }
            }
        }, "B").start();

    }
}
