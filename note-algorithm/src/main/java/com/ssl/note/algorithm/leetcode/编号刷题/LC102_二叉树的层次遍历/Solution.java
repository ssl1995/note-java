package com.ssl.note.algorithm.leetcode.编号刷题.LC102_二叉树的层次遍历;

import com.ssl.note.algorithm.leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    /**
     * 二叉树的层次遍历
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        // 根节点单独的一层
        queue.offer(root);

        List<List<Integer>> res = new ArrayList<>();

        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            // 难点：初始时，当前层的元素个数不能变，所以从size开始--
            for (int i = queue.size(); i > 0; i--) {

                TreeNode node = queue.poll();
                temp.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            res.add(temp);
        }
        return res;
    }
}
