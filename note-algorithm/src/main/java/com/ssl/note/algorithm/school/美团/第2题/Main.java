package com.ssl.note.algorithm.school.美团.第2题;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] p = new int[n];

        int[] score = new int[n];

        for (int i = 0; i < n; i++) {

            p[i] = sc.nextInt();

        }
        for (int i = 0; i < n; i++) {

            score[i] = sc.nextInt();

        }

        boolean[] flag = new boolean[n];//用来标记哪些分数已经被复习了。这样之后求和就不用再算了
        Main.getmaxscore(flag, p, 0, score, n, m, 0);

        System.out.println(Main.max);

    }


    static int count = 0;
    static double max = 0;

    public static void getmaxscore(boolean[] flag, int[] p, double sum, int[] score, int n, int m, int start) {//暴力出那m个数，再求一次和，更新当前最大值

        if (count == m) {//当找出m个数时，停止回溯，开始计算剩下的那些没复习的和，将他们相加
            for (int i = 0; i < n; i++) {

                if (!flag[i]) {//没有复习
                    sum += score[i] * p[i] * 0.01;
                }

            }
            max = Math.max(max, sum);//最后得出本次期望分数，和之前的比较，得到当前最大期望分数。然后本次递归查询结束，return后进行第二次的递归。。。重复进行 直到完毕。

            return;

        }

        for (int i = start; i < n; i++) {//回溯法的经典for循环，枚举出m个数，start是每次的起始位置
            sum += score[i];//计算这m个数的和，乘100%，所以直接相加
            flag[i] = true;//标记为已复习
            count++;//count初始为0，每回溯一次就+1；
            getmaxscore(flag, p, sum, score, n, m, start + 1);//
            sum = sum - score[i];//回溯法之撤回
            flag[i] = false;//同上
            count--;//同上
        }

    }
}
