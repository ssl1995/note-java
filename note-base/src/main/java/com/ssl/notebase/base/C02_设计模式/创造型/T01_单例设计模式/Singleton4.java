package com.ssl.notebase.base.C02_设计模式.创造型.T01_单例设计模式;

/**
 * @author SongShengLin
 * @date 2022/3/24 16:58
 * @description
 */
public class Singleton4 {

    /**
     * 加volatile防止指令重排
     */
    private static volatile Singleton4 singleton = null;

    private Singleton4() {
    }

    /**
     * 懒汉式(线程安全)
     * 优点：双端锁+volatile防止指令重排
     */
    public static Singleton4 getSingleton() {
        // 双重锁：最外层加一个锁保证线程效率
        if (singleton == null) {
            synchronized (Singleton4.class) {
                if (singleton == null) {
                    singleton = new Singleton4();
                    /**
                     * 如果没加volatile,JVM初始化对象大概有3步
                     * 1.分配内存空间
                     * 2.初始化对象
                     * 3.将singleton指向内存空间
                     * 可能会将3提前，导致下一个线程过来的时候return了一个半成品=线程不安全
                     */
                }
            }
        }
        return singleton;
    }


}
