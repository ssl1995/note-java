package com.ssl.notebase.leetcode.力扣.精选Top面试.LC124_二叉树中的最大路径和;

import com.ssl.notebase.leetcode.力扣.utils.TreeNode;

/**
 * @Author: SongShengLin
 * @Date: 2022/06/15 3:13 PM
 * @Describe:
 */
public class Solution {
    private int maxSum = Integer.MIN_VALUE;

    /**
     * 二叉树中的最大路径和
     * 输入：root = [-10,9,20,null,null,15,7]
     * 输出：42
     * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
     */
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        // 后续遍历获得左右子树最大路径和
        // 注意：左右子树最大路径和可能是负数，需要与0比较取较大值
        int left = Math.max(maxGain(node.left), 0);
        int right = Math.max(maxGain(node.right), 0);

        int pathValue = node.val + left + right;
        maxSum = Math.max(maxSum, pathValue);

        return node.val + Math.max(left, right);

    }

}
