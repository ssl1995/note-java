package com.ssl.notebase.leetcode.力扣.精选Top面试.LC322_零钱兑换;

/**
 * @author SongShengLin
 * @date 2022/6/20 23:07
 * @description
 */
public class Solution {

    /**
     * 零钱兑换
     * =完全背包问题
     * 输入：coins = [1, 2, 5], amount = 11
     * 输出：3
     * 解释：11 = 5 + 5 + 1
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        // dp[i]=选出总额为i的最少硬币数
        int[] dp = new int[amount + 1];
        // 遍历[1,amount],因为金额从1到amount
        for (int i = 1; i <= amount; i++) {
            // dp[i]所需要的最多硬币数就是它自己
            // 比如t=11，dp中i的最大值就是11，永远达不到12
            // 预设dp[i]=amount + 1
            dp[i] = amount + 1;

            // 遍历条件给的硬币数
            for (int coin : coins) {
                if (coin <= i) {
                    // i-coin位置最小值，然后再加一张coin，所以+1
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] coins = {3};
        int t = 2;
        System.out.println(solution.coinChange(coins, t));
    }
}
