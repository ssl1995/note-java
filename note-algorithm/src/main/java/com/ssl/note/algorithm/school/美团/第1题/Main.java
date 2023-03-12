package com.ssl.note.algorithm.school.美团.第1题;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        System.out.println(getMinChange(str));
        scanner.close();
    }

    /**
     * 输入一个数字字符串，最少让它连续的数字不重复的次数
     * 比如：111222333，最少需要3次修改为121212313不重复
     */
    private static int getMinChange(String str) {
        if (Objects.isNull(str)) {
            return 0;
        }
        int n = str.length();
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return str.charAt(0) == str.charAt(1) ? 1 : 0;
        }

        char[] cs = str.toCharArray();
        int[] dp = new int[n];
        if (cs[0] == cs[1]) {
            dp[1] = 1;
            cs[1] = '#';
        }

        for (int i = 2; i < n; i++) {
            if (cs[i] == cs[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                cs[i] = '#';
            } else {
                dp[i] = dp[i - 1];
            }
        }

        return dp[n - 1];
    }
}
