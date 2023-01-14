package com.ssl.note.algorithm.school.美团.第1题;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String s = scanner.next();
            res.add(longestPalindromeLen(s));
        }
        for (Integer num : res) {
            System.out.println(num);
        }
        scanner.close();
    }

    /**
     * 最长回文串长度
     */
    private static int longestPalindromeLen(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        // 初始化
        // 对角线后面那根线末尾位置，没有R-1，所以先填上
        dp[N - 1][N - 1] = 1;
        // 到N-1停止
        for (int i = 0; i < N - 1; i++) {
            // 对角线
            dp[i][i] = 1;
            // 对角线后面那根线
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        // (L,R)需要左下角(L+1,R-1)，左边(L,R-1),下边(L+1,R)三个位置
        // 所以从右下角开始，从左往右遍历
        for (int L = N - 3; L >= 0; L--) {
            // R从对角线后面的第二根线就开始往后遍历
            for (int R = L + 2; R < N; R++) {
                // 暴力递归还可以优化
                // 可能性p1 = dp[L+1][R-1]可能省掉，因为p2,p3意见算过p1
                dp[L][R] = Math.max(dp[L][R - 1], dp[L + 1][R]);
                if (str[L] == str[R]) {
                    dp[L][R] = Math.max(dp[L][R], 2 + dp[L + 1][R - 1]);
                }
            }
        }
        return dp[0][N - 1];
    }
}
