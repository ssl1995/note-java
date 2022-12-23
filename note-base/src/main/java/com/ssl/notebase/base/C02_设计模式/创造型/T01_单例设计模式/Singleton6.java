package com.ssl.notebase.base.C02_设计模式.创造型.T01_单例设计模式;

/**
 * @Author: SongShengLin
 * @Date: 2022/12/22 15:35
 * @Describe:
 */
public enum Singleton6 {
    INSTANCE;

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 枚举(线程安全)
     * 优点：防止反射、反序列化导致的单例不安全问题
     */
    public static Singleton6 getInstance() {
        return INSTANCE;
    }

}
