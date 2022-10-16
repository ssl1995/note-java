package com.ssl.notebase.algorithm.practice;

/**
 * @author SongShengLin
 * @date 2022/10/16 08:27
 * @description
 */
public class Solution {

    public int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        int negative = 2;
        if (a > 0) {
            negative--;
            a = -a;
        }
        if (b > 0) {
            negative--;
            b = -b;
        }
        int res = divi(a, b);
        return negative == 1 ? -res : res;
    }

    private int divi(int a, int b) {
        int res = 0;
        while (a <= b) {
            int value = b;
            int temp = 1;
            while (value >= (Integer.MIN_VALUE / 2) && a <= value + value) {
                temp += temp;
                value += value;
            }
            res += temp;
            a -= value;
        }
        return res;
    }
}
