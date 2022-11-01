package com.ssl.note.algorithm.leetcode.编号刷题.LC28_实现strStr;

import java.util.Arrays;

/**
 * @Author: SongShengLin
 * @Date: 2022/09/09 11:16
 * @Describe:
 */
public class Solution {

    /**
     * 输入：haystack = "hello", needle = "ll"
     * 输出：2
     * 字符串模式匹配算法
     */
    public int strStr(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] cs1 = s.toCharArray();
        char[] cs2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(cs2);// 获得待匹配串的next数组
        while (i1 < cs1.length && i2 < cs2.length) {
            if (cs1[i1] == cs2[i2]) {// i1与i2都匹配成功，与暴力法一样都后移指针
                i1++;
                i2++;
            } else if (next[i2] == -1) {// m串的遍历指针来到next[i2]=-1的位置，表示无法加速匹配，只能暴力移动i1
                i1++;
            } else {// 只要不是-1,i2就加速移动到最长后缀下一个位置,由于数组下标是0开始,所以i2移动到next[i2]下标
                i2 = next[i2];
            }
        }
        // 匹配串指针i2来到len2，代表匹配成功，匹配串在原串中的起始位置为i1-i2；否则失败返回-1
        System.out.println("i1:" + i1);
        System.out.println("i2:" + i2);
        return i2 == cs2.length ? i1 - i2 : -1;
    }

    // next数组：每个位置前字符串的前后缀最大匹配长度
    public int[] getNextArray(char[] match) {
        // 长度为1的数组,前面没有元素,规定next值为-1
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        next[0] = -1; // 第一个位置前面没有元素，规定为-1
        next[1] = 0;// 第二个位置前面1个元素，匹配个数为1
        int i = 2;// 遍历指针从第3个元素开始
        // cn两个含义:
        // 1.拿哪个位置的字符跟i-1比,由于i初始化2,i-1是1,所以cn=0
        // 2.i位置的前缀和后缀最大匹配长度=最大匹配下标+1的值
        int cn = 0;
        while (i < match.length) {
            if (match[i - 1] == match[cn]) {// i-1位置和cn相同，next[i]=cn+1,cn和i后移再匹配
                next[i++] = ++cn;
            } else if (cn > 0) {// i-1位置和cn位置不相同：有两种情况
                // 情况1：cn一直往前跳,但cn必须大于数组0位置
                cn = next[cn];
            } else {//  情况2：cn一直往前跳来到0位置，说明match[i]无前后缀匹配，next[i++]=0
                next[i++] = 0;
            }
        }
        System.out.println(Arrays.toString(match) + "的next数组=" + Arrays.toString(next));
        return next;
    }

    public static void main(String[] args) {
        String s = "abeababeabf";
        // next:[-1, 0, 0, 0, 1, 2]
        String m = "abeabf";
        Solution solution = new Solution();
        int mIndex = solution.strStr(s, m);
        System.out.println("起始位置:" + mIndex);
    }
}
