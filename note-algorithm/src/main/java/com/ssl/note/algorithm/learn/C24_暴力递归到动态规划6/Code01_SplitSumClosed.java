package com.ssl.note.algorithm.learn.C24_暴力递归到动态规划6;

public class Code01_SplitSumClosed {

    /**
     * 较小集合的累加和
     * arr分为了两个数组，尽量接近的的2个数组，返回较小数组的累加和
     */
    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        // 想到：尽量接近的2个数组，任何一个数组的累加和就是全部累计和的/2
        return process(arr, 0, sum / 2);
    }

    // arr[i...]可以自由选择，请返回累加和尽量接近rest，但不能超过rest的情况下，最接近的累加和是多少？
    public static int process(int[] arr, int i, int rest) {
        if (i == arr.length) {
            return 0;
        } else { // 还有数，arr[i]这个数
            // 可能性1，不使用arr[i]
            int p1 = process(arr, i + 1, rest);
            // 可能性2，要使用arr[i]
            int p2 = 0;
            if (arr[i] <= rest) {
                p2 = arr[i] + process(arr, i + 1, rest - arr[i]);
            }
            // 取最大
            return Math.max(p1, p2);
        }
    }

    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum /= 2;
        int N = arr.length;
        int[][] dp = new int[N + 1][sum + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= sum; rest++) {
                // 可能性1，不使用arr[i]
                int p1 = dp[i + 1][rest];
                // 可能性2，要使用arr[i]
                int p2 = 0;
                if (arr[i] <= rest) {
                    p2 = arr[i] + dp[i + 1][rest - arr[i]];
                }
                dp[i][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum];
    }

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = dp(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }

}
