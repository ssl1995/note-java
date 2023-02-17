package com.ssl.notebase.interview.demo1;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author SongShengLin
 * @date 2022/8/27 16:13
 * @description
 */
public class Main {

    /**
     * 输入a和b都是字符串形式的整数类型
     *
     * @param a
     * @param b
     * @return 加法后的整数的字符串
     */
    public String add(String a, String b) {
        if (StringUtils.isBlank(a) || StringUtils.isBlank(b)) {
            return StringUtils.isNotBlank(a) ? a : b;
        }
        // 进制转换判断
        char[] cs1 = a.toCharArray();
        char[] cs2 = b.toCharArray();
        int index1 = cs1.length - 1;
        int index2 = cs2.length - 1;

        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for (int i = index1; i >= 0; i--) {
            for (int j = index2; j >= 0; j--) {
                if (i == 0) {

                    continue;
                }

                if (j == 0) {


                    continue;
                }


                String addStr = getAddStr(cs1[i], cs2[j], carry);
                if (StringUtils.isBlank(addStr)) {
                    throw new RuntimeException("加法出现异常");
                }
                int len = addStr.length();
                if (len == 1) {
                    carry = 0;
                    sb.append(addStr);
                } else if (len == 2) {
                    carry = 1;
                    String addStrOne = addStr.substring(len - 1, len);
                    sb.append(addStrOne);
                }
            }
        }


        return sb.toString();
    }


    private String getAddStr(char a, char b, int carry) {
        if (!checkChar(a) || !checkChar(b)) {
            return "";
        }
        int cs1 = a - '0';
        int cs2 = b - '0';
        int res = cs1 + cs2;
        return String.valueOf(res + carry);

    }

    private boolean checkChar(char a) {
        int cs = a - '0';
        if (cs <= 9 && cs >= 0) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private int getNumByStr(String str) {
        if (StringUtils.isBlank(str)) {
            return 0;
        }
        int res = 0;
        int carry = 1;
        char[] cs = str.toCharArray();

        for (int i = cs.length - 1; i >= 0; i--) {
            int temp = cs[i] - '0';
            int temp2 = temp * carry;

            if (res + temp2 > Integer.MAX_VALUE) {
                throw new RuntimeException("超过整型最大值");
            }

            res += temp2;
            carry *= 10;
        }

        return res;
    }

}
