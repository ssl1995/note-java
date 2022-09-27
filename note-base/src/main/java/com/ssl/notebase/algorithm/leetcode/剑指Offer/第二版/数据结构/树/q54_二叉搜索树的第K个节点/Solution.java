package com.ssl.notebase.algorithm.leetcode.剑指Offer.第二版.数据结构.树.q54_二叉搜索树的第K个节点;


import com.ssl.notebase.algorithm.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {


    /**
     * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
     */
    public int kthLargest(TreeNode root, int k) {
        if (k < 0) {
            return -1;
        }
        // 二叉搜索树的中序遍历,用链表存数值,返回第size-k个元素就是第k大的元素
        List<Integer> list = new ArrayList<>();
        inOrder(root, list);
        // 第1大的数->排序后第size-1个数
        // 第k大的数->排序后第size-k个数
        return list.get(list.size() - k);
    }

    /**
     * 二叉搜索树，用中序遍历，保存二叉树元素进list
     */
    private void inOrder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);
    }
}
