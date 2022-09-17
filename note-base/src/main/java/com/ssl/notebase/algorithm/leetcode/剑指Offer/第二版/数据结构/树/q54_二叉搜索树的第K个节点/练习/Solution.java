package com.ssl.notebase.algorithm.leetcode.剑指Offer.第二版.数据结构.树.q54_二叉搜索树的第K个节点.练习;

import com.ssl.notebase.algorithm.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SongShengLin
 * @date 2022/1/16 3:03 PM
 * @description
 */
public class Solution {
    public int kthLargest(TreeNode root, int k) {
        if (root == null || k < 0) {
            return -1;
        }
        List<Integer> list = new ArrayList<>();
        process(root, k, list);
        return list.get(list.size() - k);
    }

    private void process(TreeNode node, int k, List<Integer> list) {
        if (node == null) {
            return;
        }
        process(node.left, k, list);
        list.add(node.val);
        process(node.right, k, list);
    }
}
