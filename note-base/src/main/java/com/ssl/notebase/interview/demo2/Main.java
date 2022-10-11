package com.ssl.notebase.interview.demo2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Main {

    public static final String RED = "red";

    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int n = scan.nextInt();
//        getStr(RED,n);
        String[] split1 = "int f(int x)".split("\\(");
        System.out.println(Arrays.toString(split1));

        String[] split2 = split1[0].split(" ");
        // 方法名和类型
        System.out.println(Arrays.toString(split2));

        String[] split3 = split1[1].split("\\)");
        // 方法参数
        System.out.println(Arrays.toString(split3));
        System.out.println(split3[0]);

    }

    private static void getStr(String str, int n) {
        if (n < 0) {
            return;
        }
        List<String> allStr = subsNoRepeat(str);
        for (String s : allStr) {
            int tempN = countSubstrings(s);
            if (tempN == n) {
                System.out.println(s);
                return;
            }
        }
    }

    public static int countSubstrings(String s) {
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

    /**
     * 字符串全部去重子序列
     */
    public static List<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        String path = "";
        HashSet<String> set = new HashSet<>();
        process2(str, 0, set, path);
        List<String> ans = new ArrayList<>();
        for (String cur : set) {
            ans.add(cur);
        }
        return ans;
    }

    public static void process2(char[] str, int index, HashSet<String> set, String path) {
        if (index == str.length) {
            set.add(path);
            return;
        }
        process2(str, index + 1, set, path);
        process2(str, index + 1, set, path + str[index]);
    }

}
