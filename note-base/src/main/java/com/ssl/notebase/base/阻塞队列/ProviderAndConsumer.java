package com.ssl.notebase.base.阻塞队列;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者与消费者
 * 阻塞队列版本
 */
public class ProviderAndConsumer<T> {

    private int length;

    private Queue<T> queue;

    private ReentrantLock lock = new ReentrantLock();

    private Condition providerCondition = lock.newCondition();
    private Condition comsumerCondition = lock.newCondition();


    public ProviderAndConsumer(int length) {
        this.length = length;
        this.queue = new LinkedList<>();
    }

    /**
     * 生产者
     */
    public void provider(T product) {
        lock.lock();
        try {
            // 如果阻塞队列长度>=初始化长度
            // 生产者就等待
            while (queue.size() >= length) {
                providerCondition.await();
            }
            // 加入阻塞队列
            queue.offer(product);
            // 消费者消费
            comsumerCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 消费者
     */
    public T consumer() {
        lock.lock();
        try {
            // 如果阻塞队列为空
            // 消费者就等待
            while (queue.isEmpty()) {
                comsumerCondition.await();
            }
            // 从阻塞队列取出产品
            T product = queue.poll();
            // 生产者生产
            providerCondition.signal();
            return product;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

}
