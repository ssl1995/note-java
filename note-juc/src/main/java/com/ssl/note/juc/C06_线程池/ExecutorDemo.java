package com.ssl.note.juc.C06_线程池;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author SongShengLin
 * @date 2022/12/24 16:49
 * @description
 */
public class ExecutorDemo {

    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public void setThreadLocal() {
        threadLocal.set("hello");
        // 及时remove才不会造成内存泄露
        threadLocal.remove();
    }


    public static void main(String[] args) {

        /**
         * 封装：new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,new SynchronousQueue<>());
         * 优点：第二个任务开始，如果第一个已经结束，那么第二个线程会服用第一个线程
         * 缺点：没法知道非核心线程数数量，最多个不确定
         */
        Executors.newCachedThreadPool();

        /**
         * 封装：new ThreadPoolExecutor(n,n,60L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
         * 优点：newFixedThreadPool的线程数是可以进行控制的，因此我们可以通过控制最大线程来使我们的服务器达到最大的使用率，
         * 同时又可以保证即使流量突然增大也不会占用服务器过多的资源
         */
        Executors.newFixedThreadPool(1);

        /**
         * 封装：new ThreadPoolExecutor(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,new DelayedWorkQueue());
         * 优点: 支持定时，以及周期性的任务执行，我们可以延迟任务的执行时间，也可以设置一个周期性的时间让任务重复执行。
         */
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.schedule(new Thread(), 1L, TimeUnit.MICROSECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new Thread(), 0L, 1L, TimeUnit.MICROSECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(new Thread(), 0L, 1L, TimeUnit.MICROSECONDS);

        /**
         * 封装：new ThreadPoolExecutor(1, 1,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>());
         * 优点：即每次只能执行一个线程任务，多余的任务会保存到一个任务队列中，等待这一个线程空闲，当这个线程空闲了再按 FIFO 方式顺序执行任务队列中的任务。
         */
        Executors.newSingleThreadExecutor();


        /**
         * 封装： new DelegatedScheduledExecutorService(new ScheduledThreadPoolExecutor(1))
         * 优点：该方法返回一个可以控制线程池内线程定时或周期性执行某任务的线程池。只不过和上面的区别是该线程池大小为 1，而上面的可以指定线程池的大小。
         */
        Executors.newSingleThreadScheduledExecutor();

    }
}
