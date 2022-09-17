package com.ssl.notebase.algorithm.leetcode.编号刷题.LC647_回文子串;

/**
 * @author SongShengLin
 * @date 2022/2/27 9:48 PM
 * @description
 */
public class Solution {
    /**
     * 回文子串
     * 回文字符串 是正着读和倒过来读一样的字符串。
     * 子字符串 是字符串中的由连续字符组成的一个序列。
     * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
     * 输入：s = "aaa"
     * 输出：6
     * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
     */
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        // dp[i][j]:表示s[i,j]上是否是回文串
        boolean[][] dp = new boolean[n][n];
        int res = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                // (i,j)位置上字母相同
                if (s.charAt(i) == s.charAt(j)) {
                    // 区间长度<=3，必为回文子串
                    if (i - j + 1 <= 3) {
                        dp[j][i] = true;
                        res++;
                    } else {
                        // 区间长度>3，状态转移
                        dp[j][i] = dp[j + 1][i - 1];
                        if (dp[j][i]) {
                            res++;
                        }
                    }
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Solution soltion = new Solution();
        String s = "aaa";
        System.out.println(soltion.countSubstrings(s));
    }
}
