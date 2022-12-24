package com.ssl.notebase.base.死锁;

/**
 * 手写一个死锁:最简单的方式
 */
public class ThreadDemo {

    public static final String LOCK1 = "lock1";
    public static final String LOCK2 = "lock2";


    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (LOCK1) {
                System.out.println(Thread.currentThread().getName() + "进入第1把锁！");
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    throw new RuntimeException("发生异常!");
                }
                synchronized (LOCK2) {
                    System.out.println(Thread.currentThread().getName() + "进入第2把锁！");
                }
            }

        }, "thread1").start();

        new Thread(() -> {
            synchronized (LOCK2) {
                System.out.println(Thread.currentThread().getName() + "进入第1把锁！");
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    throw new RuntimeException("发生异常!");
                }
                synchronized (LOCK1) {
                    System.out.println(Thread.currentThread().getName() + "进入第2把锁！");
                }
            }

        }, "thread2").start();

    }
}
