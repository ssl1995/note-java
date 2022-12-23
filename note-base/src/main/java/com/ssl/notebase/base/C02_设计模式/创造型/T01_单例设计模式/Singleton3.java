package com.ssl.notebase.base.C02_设计模式.创造型.T01_单例设计模式;

/**
 * @author SongShengLin
 * @date 2022/3/24 16:58
 * @description
 */
public class Singleton3 {

    private static Singleton3 singleton = null;

    private Singleton3() {
    }

    /**
     * 懒汉式(线程安全)
     * 优点：需要时候才创建 + 锁
     * 缺点：加上了syn，但是性能可能会有影响
     */
    public static Singleton3 getSingleton() {
        // syn+if:容易阻塞
        synchronized (Singleton3.class) {
            if (singleton == null) {
                singleton = new Singleton3();
            }
        }

        return singleton;
    }

}
