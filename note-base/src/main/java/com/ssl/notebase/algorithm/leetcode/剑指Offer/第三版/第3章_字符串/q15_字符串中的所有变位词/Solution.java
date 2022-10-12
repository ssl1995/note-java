package com.ssl.notebase.algorithm.leetcode.剑指Offer.第三版.第3章_字符串.q15_字符串中的所有变位词;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * 找到s1中所有s2的异位词
     * 输入: s = "cbaebabacd", p = "abc"
     * 输出: [0,6]
     * 解释:
     * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     */
    public List<Integer> findAnagrams(String s1, String s2) {
        List<Integer> indices = new ArrayList<>();
        if (s1.length() < s2.length()) {
            return indices;
        }
        int[] counts = new int[26];
        for (int i = 0; i < s2.length(); i++) {
            counts[s2.charAt(i) - 'a']++;
            counts[s1.charAt(i) - 'a']--;
        }
        if (areAllZero(counts)) {
            // 记录起始坐标
            indices.add(0);
        }
        for (int i = s2.length(); i < s1.length(); i++) {
            counts[s1.charAt(i) - 'a']--;
            counts[s1.charAt(i - s2.length()) - 'a']++;
            if (areAllZero(counts)) {
                // 异位词起始坐标需要+1
                indices.add(i - s2.length() + 1);
            }
        }
        return indices;
    }

    /**
     * 判断数组元素是否全为0
     *
     * @param counts
     * @return 布尔值
     */
    private boolean areAllZero(int[] counts) {
        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }
        return true;
    }
}
