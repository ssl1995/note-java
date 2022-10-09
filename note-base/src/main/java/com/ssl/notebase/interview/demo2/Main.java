package com.ssl.notebase.interview.demo2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();
        System.out.println(getSubStrTwo(str));
        scanner.close();
    }

    private static String getSubStrTwo(String str) {
        if (str == null) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            if (value > 1) {
                sb.append(entry.getKey());
            }
        }
        return sb.toString();
    }
}
