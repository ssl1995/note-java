package com.ssl.notebase.algorithm.leetcode.剑指Offer.第三版.第1章_整数的基础知识.q1_整数除法.加减乘;

/**
 * @author SongShengLin
 * @date 2022/10/15 20:16
 * @description
 */
public class AddSubMulti {


    /**
     * 手动实现一个加法，只能使用位运算
     */
    private static int add(int a, int b) {
        if (a == 0 || b == 0) {
            return a == 0 ? b : a;
        }
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    /**
     * 减法：a+b的相反数
     */
    private static int subtraction(int a, int b) {
        return add(a, oppositeNumber(b));
    }

    /**
     * 求一个数的相反数 = 补码
     * 理解补码：https://blog.csdn.net/wenxinwukui234/article/details/42119265
     */
    private static int oppositeNumber(int n) {
        return add(~n, 1);
    }

    public static void main(String[] args) {
        System.out.println(add(3, 2));
        System.out.println(oppositeNumber(3));
    }
}
