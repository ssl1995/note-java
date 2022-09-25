package com.ssl.notebase.interview.demo2;

/**
 * @author SongShengLin
 * @date 2022/9/24 20:24
 * @description
 */
public class Solution {
    public int assembleOrnaments(int x, int y, int z) {
        if (x <= 1 && y < 1) {
            return 0;
        }
        if (y <= 1 && x < 1) {
            return 0;
        }
        if (x == 1 && y == 1) {
            return z < 1 ? 0 : 1;
        }
        int res = 0;
        while (x > 0 && y > 0) {
            x--;
            y--;
            if (z > 0) {
                z--;
                res += 1;
            } else {
                if (y > 0) {
                    y--;
                    res += 1;
                }
            }

            if (x <= 0) {
                return res;
            }
            if (y <= 0) {
                return res;
            }

        }
        return res;
    }
}