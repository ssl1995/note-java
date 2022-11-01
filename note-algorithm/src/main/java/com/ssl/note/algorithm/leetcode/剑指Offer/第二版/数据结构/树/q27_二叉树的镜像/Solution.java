package com.ssl.note.algorithm.leetcode.剑指Offer.第二版.数据结构.树.q27_二叉树的镜像;


import com.ssl.note.algorithm.leetcode.utils.TreeNode;

import java.util.LinkedList;

public class Solution {

    /**
     * 输出二叉树的镜像
     * 递归法:前序遍历依次进行交换左右子树
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 先遍历到的结点的左右子树发生交换
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }

    /**
     * 输出二叉树的镜像
     * 将递归改成栈
     */
    public TreeNode mirrorTree1(TreeNode root) {
        if (root == null) {
            return null;
        }
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode top = stack.pop();
            // 交换
            TreeNode temp = top.left;
            top.left = top.right;
            top.right = temp;
            if (top.left != null) {
                stack.push(top.left);
            }
            if (top.right != null) {
                stack.push(top.right);
            }
        }
        return root;
    }
}

