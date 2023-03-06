package com.ssl.note.notealgorithm.生产者消费者模式;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author SongShengLin
 * @date 2023/3/6 17:39
 * @description
 */
public class MyResource2 {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    // 方式二：lock/condition+await+signalAll

    //生产者,规定：生产一个，消费一个
    public void productor() {
        lock.lock();
        try {
            //1 判断，生产者等待的条件：产品数量等待消费，num>0
            while (num != 0) {
                condition.await();
            }
            //2 干活
            num++;
            System.out.println(Thread.currentThread().getName() + "\tnum值：" + num);
            //3 通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //消费者
    public void consumer() {
        lock.lock();
        try {
            //1 判断 多线程是while判断
            while (num == 0) {
                condition.await();
            }
            //2 干活：消费
            num--;
            System.out.println(Thread.currentThread().getName() + "\tnum值：" + num);
            //3 通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
