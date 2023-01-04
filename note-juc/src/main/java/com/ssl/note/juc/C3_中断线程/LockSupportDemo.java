package com.ssl.note.juc.C3_中断线程;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: SongShengLin
 * @Date: 2023/01/04 20:33
 * @Describe:
 */
public class LockSupportDemo {

    /**
     * Object的wait和notify
     * 1.使用:必须搭配synchronized同步代码块或者同步方法
     * 2.先wait，再notify才能生效
     */
    @Test
    public void test1() {

        Object objectLock = new Object(); //同一把锁，类似资源类

        new Thread(() -> {
            synchronized (objectLock) {
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "被唤醒了");
        }, "t1").start();

        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
            }
        }, "t2").start();
    }

    /**
     * condition的await和signal
     * 1.使用前必须要有锁
     * 2.先await，再signal才能生效
     */
    @Test
    public void test2() {

        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t" + "start");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t" + "被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "通知了");
        }, "t2").start();
    }

    /**
     * LockSupport的park()和unPark()
     * 1.无是否加锁限制
     * 2.颠倒顺序不行，还是得先park再unpark
     */
    @Test
    public void test3() {
        //正常使用+不需要锁块
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " " + "1111111111111");
            LockSupport.park();

            System.out.println(Thread.currentThread().getName() + " " + "2222222222222------end被唤醒");
        }, "t1");
        t1.start();

        //暂停几秒钟线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LockSupport.unpark(t1);
        System.out.println(Thread.currentThread().getName() + "   -----LockSupport.unparrk() invoked over");
    }
}

