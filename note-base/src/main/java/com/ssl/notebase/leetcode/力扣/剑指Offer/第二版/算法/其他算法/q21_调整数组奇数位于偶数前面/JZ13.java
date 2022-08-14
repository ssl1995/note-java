package com.ssl.notebase.leetcode.力扣.剑指Offer.第二版.算法.其他算法.q21_调整数组奇数位于偶数前面;

public class JZ13 {
    /**
     * 牛客：保持相对次序不变，与力扣不一样
     */
    public int[] reOrderArray(int[] array) {
        int[] res = new int[array.length];
        int index = 0;
        // 先遍历奇数
        for (int num : array) {
            if (num % 2 != 0) {
                res[index++] = num;
            }
        }
        // 再遍历偶数
        for (int num : array) {
            if (num % 2 == 0) {
                res[index++] = num;
            }
        }
        return res;
    }
}
