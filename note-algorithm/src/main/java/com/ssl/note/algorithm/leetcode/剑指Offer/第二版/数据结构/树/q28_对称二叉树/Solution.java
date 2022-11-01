package com.ssl.note.algorithm.leetcode.剑指Offer.第二版.数据结构.树.q28_对称二叉树;


import com.ssl.note.algorithm.leetcode.utils.TreeNode;

public class Solution {

    /**
     * 判断二叉树是否对称
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return recur(root.left, root.right);
    }

    private boolean recur(TreeNode left, TreeNode right) {
        // base1:如果两者同时越过叶子结点，对称成功，返回true
        if (left == null && right == null) {
            return true;
        }
        // base2:左右结点一个到达null,另一个不到达,或者左右结点值不相同，对称失败，返回false
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        // 前序遍历和对称前序遍历序列是否相同：左右遍历 && 右左遍历
        return recur(left.left, right.right) && recur(left.right, right.left);
    }
}
