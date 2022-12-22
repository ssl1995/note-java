package com.ssl.notebase.base.设计模式.创造型.单例设计模式;

/**
 * @Author: SongShengLin
 * @Date: 2022/12/22 15:26
 * @Describe:
 */
public class Singleton5 {

    private Singleton5() {
    }

    /**
     * 静态内部类(线程安全)
     * 优点：延迟加载、高并发、线程安全由jvm保证
     */
    public static class Singleton5Inner {
        public static Singleton5 instance = new Singleton5();
    }

    public static Singleton5 getInstance() {
        return Singleton5Inner.instance;
    }
}
