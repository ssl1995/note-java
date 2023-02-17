package com.ssl.notebase.base.test;

/**
 * @Author: SongShengLin
 * @Date: 2023/02/13 09:26
 * @Describe:
 */
public class Class2 implements Test2, Test3 {
    @Override
    public void test2() {
        System.out.println("test2");

    }

    @Override
    public void test3() {
        System.out.println("test3");

    }

    public static void main(String[] args) {
        Test2 test2 = new Class1();
        test2.test2();

        Test1 test1 = (Test1) test2;
        test1.test1();

        Test3 test3 = (Test3) test2;
        test3.test3();

    }
}
