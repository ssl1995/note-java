package com.ssl.notebase.leetcode.力扣.热门100.LC312_戳气球;

/**
 * @author SongShengLin
 * @date 2022/2/24 12:30 AM
 * @description
 */
public class Solution2 {

    /**
     * 戳气球
     * 输入：nums = [3,1,5,8]
     * 输出：167
     * 解释：
     * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
     * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
     */
    public int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        // arr保存nums前后不存在的两个数
        int[] arr = new int[n + 2];
        // 初始化前后不存在的两个数
        arr[0] = 1;
        arr[n + 1] = 1;
        System.arraycopy(nums, 0, arr, 1, n);

        // dp[i][j]表示(i,j)开区间上，添加一个气球获得的最大硬币数
        // 注意：是开区间，取不到[i]和[j]位置的数
        int[][] dp = new int[n + 2][n + 2];

        // base case: dp[i][j]当i=j时均为0
        // 结果：dp[0][n+1] 思考自底向上的遍历方式
        for (int i = n; i >= 0; i--) {
            for (int j = i + 1; j < n + 2; j++) {
                // [i,j]中戳破那个气球，寻找其中最大值
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], arr[i] * arr[k] * arr[j] + dp[i][k] + dp[k][j]);
                }
            }
        }

        return dp[0][n + 1];
    }


    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int[] nums = {3, 1, 5, 8};
        System.out.println(solution.maxCoins(nums));
    }

}
