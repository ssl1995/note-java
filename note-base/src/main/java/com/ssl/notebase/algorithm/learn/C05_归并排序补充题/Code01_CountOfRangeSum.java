package com.ssl.notebase.algorithm.learn.C05_归并排序补充题;

// 这道题直接在leetcode测评：
// https://leetcode.com/problems/count-of-range-sum/
// https://leetcode.cn/problems/count-of-range-sum/
public class Code01_CountOfRangeSum {

    /**
     * LC327 区间数的个数
     * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
     * 输入：nums = [-2,5,-1], lower = -2, upper = 2
     * 输出：3
     * 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
     */
    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 前缀和数组：便于获取arr[i,j]的累加和
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum, 0, sum.length - 1, lower, upper);
    }

    public static int process(long[] sum, int L, int R, int lower, int upper) {
        if (L == R) {
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }
        int M = L + ((R - L) >> 1);
        return process(sum, L, M, lower, upper)
                + process(sum, M + 1, R, lower, upper)
                + merge(sum, L, M, R, lower, upper);
    }

    public static int merge(long[] preSum, int L, int M, int R, int lower, int upper) {
        int ans = 0;
        int windowL = L;
        int windowR = L;
        // [windowL, windowR)
        for (int i = M + 1; i <= R; i++) {
            // 已知preSum[i]和[lower,upper]
            // 可以推算出preSum[0..i-1]有多少在[lower-preSum[i],upper-preSum[i]]
            long min = preSum[i] - upper;
            long max = preSum[i] - lower;
            while (windowR <= M && preSum[windowR] <= max) {
                windowR++;
            }
            while (windowL <= M && preSum[windowL] < min) {
                windowL++;
            }
            ans += windowR - windowL;
        }

        // 归并排序
        long[] help = new long[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = preSum[p1] <= preSum[p2] ? preSum[p1++] : preSum[p2++];
        }
        while (p1 <= M) {
            help[i++] = preSum[p1++];
        }
        while (p2 <= R) {
            help[i++] = preSum[p2++];
        }
        for (i = 0; i < help.length; i++) {
            preSum[L + i] = help[i];
        }
        return ans;
    }

}
