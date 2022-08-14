package com.ssl.notebase.leetcode.力扣.热门100.LC41_接雨水;

/**
 * @author SongShengLin
 * @date 2022/1/23 10:36 PM
 * @description
 */
public class Solution {
    /**
     * 接雨水
     * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出：6
     */
    public int trap(int[] arr) {
        // 双指针
        int left = 0, right = arr.length - 1;
        // 桶高
        int height = 0;
        // 如果返回值是long，那么雨水量类型就是long
        // 本题是int类型
        int water = 0;

        while (left < right) {
            // 桶高：左右指针、历史桶高的最大值
            height = Math.max(height, Math.min(arr[left], arr[right]));

            // 雨水量 += 最低高度 - 左右最矮
            water += arr[left] <= arr[right] ? (height - arr[left++]) : (height - arr[right--]);
        }
        return water;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(solution.trap(nums));
    }
}
