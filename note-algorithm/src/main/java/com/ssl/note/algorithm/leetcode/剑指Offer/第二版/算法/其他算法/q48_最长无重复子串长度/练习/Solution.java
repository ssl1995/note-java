package com.ssl.note.algorithm.leetcode.剑指Offer.第二版.算法.其他算法.q48_最长无重复子串长度.练习;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SongShengLin
 * @date 2022/1/14 9:02 AM
 * @description
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), -1);
        }
        int pre = -1;

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) >= pre) {
                pre = map.get(s.charAt(i));
            }
            res = Math.max(res, i - pre);

            map.put(s.charAt(i), i);
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abcabcbb";
        System.out.println(solution.lengthOfLongestSubstring(s));
    }
}
