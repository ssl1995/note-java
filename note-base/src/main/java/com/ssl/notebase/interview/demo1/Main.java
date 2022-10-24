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

    public boolean checkInclusion(String s1, String s2) {
        if (s2 == null || s1.length() < s2.length()) {
            return false;
        }
        int[] map = new int[256];
        int M = s1.length();
        int N = s2.length();
        for (int i = 0; i < N; i++) {
            map[s2.charAt(i) - 'a']++;
            map[s1.charAt(i) - 'a']--;
        }
        if (checkZero(map)) {
            return true;
        }

        for (int i = N; i < M; i++) {
            map[s1.charAt(i) - 'a']--;
            if (checkZero(map)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkZero(int[] count) {
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }


}
