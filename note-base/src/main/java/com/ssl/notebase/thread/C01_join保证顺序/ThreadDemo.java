package com.ssl.notebase.thread.C01_join保证顺序;

/**
 * @Author: SongShengLin
 * @Date: 2022/12/01 22:05
 * @Describe:
 */
public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> System.out.println("Thread1"));
        Thread thread2 = new Thread(() -> System.out.println("Thread2"));
        Thread thread3 = new Thread(() -> System.out.println("Thread3"));
        // 使用join保证线程顺序
        thread1.start();
        thread1.join();

        thread2.start();
        thread2.join();

        thread3.start();
        thread3.join();
    }
}
