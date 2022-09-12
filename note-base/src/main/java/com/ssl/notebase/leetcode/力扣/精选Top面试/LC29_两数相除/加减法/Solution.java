package com.ssl.notebase.leetcode.力扣.精选Top面试.LC29_两数相除.加减法;

/**
 * @Author: SongShengLin
 * @Date: 2022/09/12 17:02
 * @Describe:
 */
public class Solution {

    // 加法:无进位相加(^)+进位的结果(&<<1)
    public static int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;// sum:记录^的结果
            b = (a & b) << 1;// b:进位的结果，进位信息需要左移才能相加
            a = sum;
        }
        return sum;
    }

    // 减法:a-b=a+(-b)
    public static int minus(int a, int b) {
        return add(a, negNum(b));
    }

    // 获得一个数相反数:取反+1
    public static int negNum(int n) {
        return add(~n, 1);
    }

    // 乘法:左移a,无符号右移b
    public static int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            // b末尾是1,说明要加1个a
            if ((b & 1) != 0) {
                res = add(res, a);
            }
            // a有符号左移一位
            a <<= 1;
            // b无符号右移一位,因为需要将b磨成0,所以是无符号右移缩小
            b >>>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("n:" + n);
        System.out.println("~n:" + ~n);
        int n1 = n;
        n1 <<= 1;
        System.out.println("<<n:" + n1);
        System.out.println("-n:" + negNum(n));

        int a = 2;
        int b = 3;
        System.out.println(multi(a, b));
    }

    int INF = Integer.MAX_VALUE;
    public int divide(int _a, int _b) {
        long a = _a, b = _b;
        boolean flag = false;
        if ((a < 0 && b > 0) || (a > 0 && b < 0)) flag = true;
        if (a < 0) a = -a;
        if (b < 0) b = -b;


        long l = 0, r = a;
        while (l < r) {
            long mid = l + r + 1 >> 1;
            if (mul(mid, b) <= a) l = mid;
            else r = mid - 1;
        }
        r = flag ? -r : r;
        if (r > INF || r < -INF - 1) return INF;
        return (int)r;
    }
    long mul(long a, long k) {
        long ans = 0;
        while (k > 0) {
            if ((k & 1) == 1) ans += a;
            k >>= 1;
            a += a;
        }
        return ans;
    }


}
