package com.ssl.notebase.interview.demo1;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author SongShengLin
 * @date 2022/8/27 16:13
 * @description
 */
public class Main {

    public static final String BAIDU = "Baidu";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            printBaidu(scan.next());
        }
    }

    private static void printBaidu(String str) {
        if (str == null) {
            System.out.println("No");
        } else {
            List<String> permutation = permutation(str);
            if (permutation.contains(BAIDU)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    private static List<String> permutation(String s) {
        if (s == null) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        char[] cs = s.toCharArray();
        process(cs, 0, res);
        return res;
    }

    private static void process(char[] cs, int index, List<String> res) {
        if (index == cs.length) {
            res.add(String.valueOf(cs));
        } else {
            boolean[] visited = new boolean[256];
            for (int i = index; i < cs.length; i++) {
                if (!visited[cs[i]]) {
                    visited[cs[i]] = true;
                    swap(cs, index, i);
                    process(cs, index + 1, res);
                    swap(cs, index, i);
                }
            }
        }
    }

    private static void swap(char[] cs, int i, int j) {
        if (i == j) {
            return;
        }
        char temp = cs[i];
        cs[i] = cs[j];
        cs[j] = temp;
    }


}
