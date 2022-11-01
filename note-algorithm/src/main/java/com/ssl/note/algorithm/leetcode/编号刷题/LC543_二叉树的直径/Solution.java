package com.ssl.note.algorithm.leetcode.编号刷题.LC543_二叉树的直径;

import com.ssl.note.algorithm.leetcode.utils.TreeNode;

/**
 * @author SongShengLin
 * @date 2022/2/27 12:01 AM
 * @description
 */
public class Solution {

    private int max;

    /**
     * 二叉树的直径
     */
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return max;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = dfs(node.left);
        int right = dfs(node.right);

        // 直径=两结点间长度=left+right
        max = Math.max(max, left + right);

        // 深度=左右子树最大值+1
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;

        System.out.println(solution.diameterOfBinaryTree(node1));

    }
}
