package com.ssl.note.juc.C02_锁;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: SongShengLin
 * @Date: 2023/01/04 08:04
 * @Describe: 演示非公平锁
 */
public class SaleTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        // ReentrantLock默认是非公平，谁先抢到线程谁先执行
        new Thread(() -> {
            for (int i = 0; i < 35; i++)
                ticket.sale();
        }, "a").start();

        new Thread(() -> {
            for (int i = 0; i < 35; i++)
                ticket.sale();
        }, "b").start();

        new Thread(() -> {
            for (int i = 0; i < 35; i++)
                ticket.sale();
        }, "c").start();
    }
}

class Ticket {
    private int number = 30;
    ReentrantLock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第：\t" + (number--) + "\t 还剩下:" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}



