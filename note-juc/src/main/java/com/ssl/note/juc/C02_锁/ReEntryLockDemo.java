package com.ssl.note.juc.C02_锁;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: SongShengLin
 * @Date: 2023/01/04 08:17
 * @Describe:
 */
public class ReEntryLockDemo {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        final Object objectLockA = new Object();
        // syn是可重入锁
        // syn可重入的原因：锁对象的加锁时候判断是否当前线程持有的锁，再次进入时候monitorenter计数器+1；解锁的时候一次-1
        new Thread(() -> {
            synchronized (objectLockA) {
                System.out.println("-----外层调用");
                synchronized (objectLockA) {
                    System.out.println("-----中层调用");
                    synchronized (objectLockA) {
                        System.out.println("-----内层调用");
                    }
                }
            }
        }, "a").start();

//        ReEntryLockDemo reEntryLockDemo = new ReEntryLockDemo();
//        reEntryLockDemo.m1();


        // ReentrantLock也是可重入的
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("----外层调用lock");
                lock.lock();
                try {
                    System.out.println("----内层调用lock");
                } finally {
                // 这里故意注释，实现加锁次数和释放次数不一样
                // 由于加锁次数和释放次数不一样，第二个线程始终无法获取到锁，导致一直在等待。
                    lock.unlock(); // 正常情况，加锁几次就要解锁几次
                }
            } finally {
                lock.unlock();
            }
        }, "a").start();

    }

    public synchronized void m1() {
        System.out.println("-----m1");
        m2();
    }

    public synchronized void m2() {
        System.out.println("-----m2");
        m3();
    }

    public synchronized void m3() {
        System.out.println("-----m3");
    }

}


