package com.ssl.note.algorithm.leetcode.剑指Offer.第二版.算法.搜索算法.q04_二维数组查找;

public class Solution {


    /**
     * 二维数组中的查找，元素值从上到小、从左到右递增
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        // 最左下角坐标:[matrix.length-1][0]
        int i = matrix.length - 1;
        int j = 0;
        while (i >= 0 && j <= matrix[0].length - 1) {
            if (matrix[i][j] < target) {
                j++;
            } else if (matrix[i][j] > target) {
                i--;
            } else {
                return true;
            }
        }
        return false;
    }
}
