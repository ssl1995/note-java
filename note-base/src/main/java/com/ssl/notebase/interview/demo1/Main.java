package com.ssl.notebase.interview.demo1;


import java.util.*;

/**
 * @author SongShengLin
 * @date 2022/8/27 16:13
 * @description
 */
public class Main {

    public static void main(String[] args) {
        int n = 3;
        for (int i = 0; i < 10; i++) {
            // 测试一下
            System.out.println(getNStr(n));
        }

    }

    /**
     * 输入一个n，返回一个指定n长度的01字符串
     */
    private static String getNStr(int n) {
        char[] subCs = new char[2];
        subCs[0] = '0';
        subCs[1] = '1';

        char[] cs = new char[n];
        for (int i = 0; i < n; i++) {
            cs[i] = subCs[(int) ((1 + 1) * Math.random()) - (int) (1 * Math.random())];
        }

        return new String(cs);
    }


}
