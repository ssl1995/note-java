package com.ssl.notebase.algorithm.leetcode.编号刷题.LC238_除自身以外数组的乘积;

import java.util.Arrays;

/**
 * @author SongShengLin
 * @date 2022/2/22 9:12 PM
 * @description
 */
public class Solution {
    /**
     * 除自身以外数组的乘积
     * 给你一个整数数组 nums，返回 数组 answer ，
     * 其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
     * 输入: nums = [1,2,3,4]
     * 输出: [24,12,8,6]
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int n = nums.length;
        int[] res = new int[n];

        // 初始化0位置元素为1
        res[0] = 1;
        // 统计i左边所有元素乘积
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }

        int temp = 1;
        // 统计右边所有元素乘积
        for (int i = n - 2; i >= 0; i--) {
            temp *= nums[i + 1];

            res[i] *= temp;
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 2, 3, 4};
        System.out.println(Arrays.toString(solution.productExceptSelf(nums)));
    }
}
