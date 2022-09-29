package com.ssl.notebase.interview.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/9/24 20:01
 * @description
 */
public class Solution {

    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        int[] flags = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                flags[i] |= 1 << (c - 'a');
            }
        }

        int max = 0;
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
//                if (flags[i] != flags[j]) {
//                    max = Math.max(max, words[i].length() * words[j].length());
//                }
                if ((flags[i] & flags[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }


        return max;
    }


}
