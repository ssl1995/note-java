package com.ssl.notebase.algorithm.leetcode.剑指Offer.第三版.第3章_字符串.q18_有效的回文;

public class Solution {

    /**
     * 判断回文串
     */
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char c1 = s.charAt(i);
            char c2 = s.charAt(j);
            // 只考虑字符和数字，忽略其他字符和数字
            if (!Character.isLetterOrDigit(c1)) {
                i++;
            } else if (!Character.isLetterOrDigit(c2)) {
                j--;
            } else {
                // 将所有大写转换为小写
                c1 = Character.toLowerCase(c1);
                c2 = Character.toLowerCase(c2);
                if (c1 != c2) {
                    return false;
                }
                i++;
                j--;
            }
        }
        return true;
    }
}
