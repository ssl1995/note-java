package com.ssl.notebase.learn.设计模式.创造型.单例设计模式;

/**
 * @author SongShengLin
 * @date 2022/3/24 16:58
 * @description
 */
public class Singleton3 {

    private static Singleton3 singleton = null;

    private Singleton3() {
    }

    public static Singleton3 getSingleton() {
        // syn+if:容易阻塞
        synchronized (Singleton3.class) {
            if (singleton == null) {
                singleton = new Singleton3();
            }
        }

        return singleton;
    }

//    第二种写法
//    public static synchronized Singleton3 getSingleton1() {
//        if (singleton == null) {
//            singleton = new Singleton3();
//        }
//        return singleton;
//    }


}
