package com.ssl.notebase.base.test;

/**
 * @Author: SongShengLin
 * @Date: 2023/02/13 09:26
 * @Describe:
 */
public class Class1 implements Test1,Test2{
    @Override
    public void test1() {
        System.out.println("test1");
    }

    @Override
    public void test2() {
        System.out.println("test2");

    }
}
