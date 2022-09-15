package com.ssl.notebase.coding;

import java.util.Scanner;

/**
 * @author SongShengLin
 * @date 2022/8/27 16:13
 * @description
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 5
        int x = sc.nextInt();
        sc.close();
        // 2
        int n = (int) (Math.log(x) / Math.log(2.0));
        char c = (char) ('a' + n);
        // 1 or 2 or 0
        int next = x - (int) Math.pow(2, n);

        if (next == 0) {
            System.out.println(c);
        } else if (next == 1) {
            String s = String.valueOf(c);
            s = (s + "a");
            System.out.println(s);
        } else if (next == 2) {
            String s = String.valueOf(c);
            s = (s + "b");
            System.out.println(s);
        } else {
            String s = String.valueOf(c);
            s = (s + "ba");
            System.out.println(s);
        }
    }

}
