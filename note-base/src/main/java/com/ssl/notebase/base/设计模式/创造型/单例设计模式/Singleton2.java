package com.ssl.notebase.base.设计模式.创造型.单例设计模式;

/**
 * @author SongShengLin
 * @date 2022/3/24 16:58
 * @description
 */
public class Singleton2 {

    private static Singleton2 instance;

    private Singleton2() {
    }

    /**
     * 懒汉式(线程不安全)
     * 优点：需要时候才创建
     * 缺点：线程不安全
     */
    public static Singleton2 getInstance() {
        if (instance == null) {
            // 2个线程可以同时到达这里，造成线程不安全的情况
            instance = new Singleton2();
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton2 singleton1 = Singleton2.getInstance();
        Singleton2 singleton2 = Singleton2.getInstance();
        System.out.println(singleton1.equals(singleton2));
    }


}
