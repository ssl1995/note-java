package com.ssl.note.notealgorithm.生产者消费者模式;

/**
 * @author SongShengLin
 * @date 2023/3/6 17:40
 * @description
 */
public class MyResource2Test {
    public static void main(String[] args) {
        MyResource2 myResources = new MyResource2();

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    myResources.productor();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "线程1").start();
        }

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                try {
                    myResources.consumer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, "线程2").start();
        }
    }

}
