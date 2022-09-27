package com.ssl.notebase.algorithm.leetcode.剑指Offer.第三版.第15章_图.q119_最长连续序列;

import java.util.*;

/**
 * @author SongShengLin
 * @date 2021/11/29 5:07 下午
 * @description
 */
public class Solution {
    // 用图的广度优先搜索，速度比并查集快
    public int longestConsecutive(int[] nums) {
        // set用来判断某个数字是否存在
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;
        while (!set.isEmpty()) {
            Iterator<Integer> iterator = set.iterator();
            longest = Math.max(longest, dfs(set, iterator.next()));
        }

        return longest;
    }

    private int dfs(Set<Integer> set, int num) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(num);
        set.remove(num);
        // 一个数也是一个子图，所以len初始化为1
        int length = 1;
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            // 连续，找的是当前遍历数字的前后相近的数字
            // 比如，num=1,前后两个数是0,3
            int[] neighbors = {poll - 1, poll + 1};

            for (int neighbor : neighbors) {
                if (set.contains(neighbor)) {
                    queue.offer(neighbor);
                    set.remove(neighbor);
                    length++;
                }
            }
        }
        return length;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {};
        System.out.println(solution.longestConsecutive(nums));
    }
}
