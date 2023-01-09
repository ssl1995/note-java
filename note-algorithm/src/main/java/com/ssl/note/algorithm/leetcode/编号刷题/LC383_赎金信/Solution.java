package com.ssl.note.algorithm.leetcode.编号刷题.LC383_赎金信;


/**
 * @Author: SongShengLin
 * @Date: 2023/01/09 20:12
 * @Describe:
 */
public class Solution {

    /**
     * 赎金信
     * 输入：ransomNote = "a", magazine = "b"
     * 输出：false
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        // magazine中的元素每次只能用1次
        // 并且只有小写的字母
        int[] map = new int[26];
        for (char c : magazine.toCharArray()) {
            map[c - 'a']++;
        }

        for (char c : ransomNote.toCharArray()) {
            map[c - 'a']--;
            if (map[c - 'a'] < 0) {
                return false;
            }
        }

        return true;

    }
}
