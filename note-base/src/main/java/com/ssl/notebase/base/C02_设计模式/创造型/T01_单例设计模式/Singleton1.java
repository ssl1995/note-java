package com.ssl.notebase.base.C02_设计模式.创造型.T01_单例设计模式;

/**
 * @author SongShengLin
 * @date 2022/3/24 16:58
 * @description
 */
public class Singleton1 {

    /**
     * 饿汉式
     * 在类加载期间初始化实例
     * 优点：生成类的速度快、线程安全
     * 缺点：如果是大对象，浪费内存
     */
    private static Singleton1 singleton = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getSingleton() {
        // 直接new ：如果多次不用，饿汉式浪费内存
        return singleton;
    }

    public static void main(String[] args) {
        Singleton1 singleton1 = Singleton1.getSingleton();
        Singleton1 singleton2 = Singleton1.getSingleton();
        System.out.println(singleton1.equals(singleton2));
    }

}
