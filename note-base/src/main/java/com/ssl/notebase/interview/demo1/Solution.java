package com.ssl.notebase.interview.demo1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/9/24 20:01
 * @description
 */
public class Solution {
    /**
     * 最长公共文本序列
     *
     * @param stringA
     * @param stringB
     * @return
     */
    public String[] findMaxCommon(String stringA, String stringB) {
        if (stringA == null || stringA.length() == 0 || stringB == null || stringB.length() == 0) {
            return new String[]{""};
        }
        List<String> res = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        StringBuilder next = new StringBuilder();

        String[] csA = stringA.split(" ");
        String[] csB = stringB.split(" ");
        for (int i = 0; i < csA.length; i++) {
            for (int j = 0; j < csB.length; j++) {
                String s1 = csA[i];
                String s2 = csB[j];
                if (strEquals(s1, s2)) {
                    temp.append(s1);
                    if(j!= csA.length-1){
                        temp.append(" ");
                    }
                }
            }
            res.add(temp.toString());
        }

        String[] resS = new String[1];
//        for (int i = 0; i < res.size(); i++) {
//            resS[i] = res.get(i);
//        }
        resS[0] = res.get(res.size() - 1);
        return resS;
    }

    private boolean strEquals(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int i = 0;
        int j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) != s2.charAt(j)) {
                return false;
            }
            i++;
            j++;
        }
        return true;
    }

//    public static void main(String[] args) {
//        String s1 = "A new social, create your hip play show";
//        String s2 = "A new social, and create your hip show";
//        Solution solution = new Solution();
//        System.out.println(Arrays.toString(solution.findMaxCommon(s1, s2)));
//    }

}
