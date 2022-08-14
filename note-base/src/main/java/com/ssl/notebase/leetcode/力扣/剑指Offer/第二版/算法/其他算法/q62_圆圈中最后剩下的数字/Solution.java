package com.ssl.notebase.leetcode.力扣.剑指Offer.第二版.算法.其他算法.q62_圆圈中最后剩下的数字;

public class Solution {

    /**
     * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     * 约瑟夫回环问题
     */
    public int lastRemaining(int n, int m) {
        if (n < 1 || m < 1) {
            return -1;
        }
        // f(n,m)表示每次在n个数字0...n-1中删除第m个数字后剩下的数字
        // 显然f(n,m)=f'(n-1,m)
        // 得到n个数的序列中最后剩下的数字，只需要得到n-1个数字序列中最后剩下的数字和0
        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }
        return last;
    }
}
