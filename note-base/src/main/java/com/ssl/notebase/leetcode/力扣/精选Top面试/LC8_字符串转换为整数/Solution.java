package com.ssl.notebase.leetcode.力扣.精选Top面试.LC8_字符串转换为整数;

/**
 * @Author: SongShengLin
 * @Date: 2022/09/05 12:44
 * @Describe:
 */
public class Solution {
    /**
     * 字符串转换为整数
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     */
    public int myAtoi(String str) {
        // 去掉首尾空格:原数组去首位空格后转换为字符数组
        char[] c = str.trim().toCharArray();
        if (c.length == 0) {
            return 0;
        }
        // num越界的两种情况:int型最大值21474836472147483647,
        // 由于最后num=num*10+字符数,所以边界需要/10
        int maxBoundary = Integer.MAX_VALUE / 10;
        // sign:正负号标记,1正号,-1负号
        int index = 0, sign = 1;
        int num = 0;
        // 第一个部分有三种情况:+/-/数字
        if (c[0] == '-') {
            sign = -1;
            index = 1;
        } else if (c[0] == '+') {
            index = 1;
        }
        for (int i = index; i < c.length; i++) {
            // 力扣：遇到非数字部分，可以忽略，直接当前得到的整数
            if (c[i] < '0' || c[i] > '9') {
                break;
            }
            // 1.num=最大值/10 且 c[i]>'7',乘积后必越界
            // 2.num>最大值/10 乘积后必越界
            if (num > maxBoundary || (num == maxBoundary && c[i] > '7')) {
                // 越界后，返回整型最值
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            // 不越界，才拼接数字部分
            num = num * 10 + (c[i] - '0');
        }
        // 返回符号*数字
        return sign * num;
    }
}
