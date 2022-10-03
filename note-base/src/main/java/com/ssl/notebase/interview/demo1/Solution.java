package com.ssl.notebase.interview.demo1;

import com.ssl.notebase.algorithm.leetcode.utils.TreeNode;


/**
 * @author SongShengLin
 * @date 2022/9/24 20:01
 * @description
 */
public class Solution {

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isBST;
    }

    public static class Info {
        public boolean isBST;
        // 每个结点的最大值和最小值都要
        public int max;
        public int min;

        public Info(boolean i, int ma, int mi) {
            isBST = i;
            max = ma;
            min = mi;
        }

    }

    public static Info process(TreeNode x) {
        if (x == null) {
            // 空树：不知道怎么设置，就返回null，在上游处理
            return null;
        }
        // x左树、右树信息
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        // x的最大值
        int max = x.val;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
        }
        // x的最小值
        int min = x.val;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
        }
        // x的平衡性
        boolean isBST = true;
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        if (leftInfo != null && leftInfo.max >= x.val) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.min <= x.val) {
            isBST = false;
        }
        return new Info(isBST, max, min);
    }


}
