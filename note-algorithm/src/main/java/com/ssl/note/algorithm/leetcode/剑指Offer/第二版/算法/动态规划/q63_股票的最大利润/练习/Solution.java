package com.ssl.note.algorithm.leetcode.剑指Offer.第二版.算法.动态规划.q63_股票的最大利润.练习;

/**
 * @author SongShengLin
 * @date 2022/1/17 9:26 AM
 * @description
 */
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int minPrice = prices[0];
        int maxProfit = 0;
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }
}
