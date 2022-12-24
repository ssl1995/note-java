package com.ssl.notebase.base.多线程.printABC;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author SongShengLin
 * @date 2022/12/24 11:27
 * @description
 */
public class PrintABC {

    private int count;
    private volatile int value = 0;

    private ReentrantLock lock = new ReentrantLock();

    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public PrintABC(int count) {
        this.count = count;
    }

    public void printABC() {
        new Thread(new PrintA()).start();
        new Thread(new PrintB()).start();
        new Thread(new PrintC()).start();
    }


    class PrintA implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < count; i++) {
                    while (value % 3 != 0) {
                        conditionA.await();
                    }
                    System.out.print("A");
                    conditionB.signal();
                    value++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    class PrintB implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < count; i++) {
                    while (value % 3 != 1) {
                        conditionB.await();
                    }
                    System.out.print("B");
                    conditionC.signal();
                    value++;
                }
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
    }

    class PrintC implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < count; i++) {
                    while (value % 3 != 2) {
                        conditionC.await();
                    }
                    System.out.println("C");
                    conditionA.signal();
                    value++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

    public static void main(String[] args) {
        /**
         * ABC
         * ABC
         * ABC
         * ABC
         * ABC
         */
        PrintABC printABC = new PrintABC(5);
        printABC.printABC();
    }
}
