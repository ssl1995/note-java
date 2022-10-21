package com.ssl.notebase.algorithm.learn.C27_KMP;

public class Code03_IsRotation {

    /**
     * 判断b是否是a的旋转串
     */
    public static boolean isRotation(String a, String b) {
        // 长度不相同
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }
        // 拼接两次b = 自己拼接自己，任何长度为自己的子串都是b2的子串
        String b2 = b + b;
        // 使用kmp算法判断a是否是b2的子串
        return getIndexOf(b2, a) != -1;
    }

    // KMP Algorithm
    public static int getIndexOf(String s, String m) {
        if (s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str1 = "yunzuocheng";
        String str2 = "zuochengyun";
        System.out.println(isRotation(str1, str2));

    }

}
