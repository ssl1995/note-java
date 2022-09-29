package com.ssl.notebase.algorithm.leetcode.剑指Offer.第三版.第2章_数组.q8_和大于或等于k的最短子数组;

/**
 * @author SongShengLin
 * @date 2021/9/14 9:04 上午
 */
public class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;// left、right初始化指向数组第一位
        int sum = 0;// 记录和
        int minLen = Integer.MAX_VALUE;// minLen：记录最短距离
        for (int right = 0; right < nums.length; right++) {// sum<t，right右移
            sum += nums[right];
            // 判断是否需要右移left：sum>=t，说明sum太大了，右移left
            while (left <= right && sum >= target) {
                // minLen：记录right和left的距离最小值
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left++];
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {2, 3, 1, 2, 4, 3};
        int t = 7;
        System.out.println(solution.minSubArrayLen(t, nums));
    }
}
