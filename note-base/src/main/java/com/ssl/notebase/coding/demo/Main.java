package com.ssl.notebase.coding.demo;


import java.util.*;

/**
 * @author SongShengLin
 * @date 2022/8/27 16:13
 * @description
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        sc.close();
        int res = 0;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            for (int j = 1; j <= str.length() - i; j++) {
                String sub = str.substring(i, i + j);
//                System.out.println(sub);
                list.add(sub);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (checkGoodStr(list.get(i))) {
                res++;
            }
        }


        System.out.println(res);
    }

    /**
     * a
     * ab
     * aba
     * abab
     * ababa
     * b
     * ba
     * bab
     * baba
     * a
     * ab
     * aba
     * b
     * ba
     * a
     * @param sub
     * @return
     */
    private static boolean checkGoodStr(String sub) {
        char[] cs = sub.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : cs) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        // 偶数
        int cn1 = 0;
        // 奇数
        int cn2 = 0;
        for (Integer value : map.values()) {
            if (value % 2 == 0) {
                cn1++;
            } else {
                cn2++;
            }
        }
        if (cn2 != 1) {
            return false;
        }
        return true;
    }

}
