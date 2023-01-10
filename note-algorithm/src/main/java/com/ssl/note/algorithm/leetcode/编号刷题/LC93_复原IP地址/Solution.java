package com.ssl.note.algorithm.leetcode.编号刷题.LC93_复原IP地址;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Author: SongShengLin
 * @Date: 2023/01/09 20:51
 * @Describe:
 */
public class Solution {

    /**
     * 复原IP地址
     * 输入：s = "101023"
     * 输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
     */
    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        List<String> res = new ArrayList<>();
        // 1.最多12位，最少4位
        if (len > 12 || len < 4) {
            return res;
        }

        Deque<String> path = new ArrayDeque<>(4);
        dfs(s, len, 0, 4, path, res);
        return res;
    }


    private void dfs(String s, int len, int begin, int residue, Deque<String> path, List<String> res) {
        // 1.递归结束条件
        if (begin == len) {
            if (residue == 0) {
                res.add(String.join(".", path));
            }
            return;
        }
        // 2.每一个位置最多递归3次
        for (int i = begin; i < begin + 3; i++) {
            // 超过字符串长度
            if (i >= len) {
                break;
            }
            // 一个ip最多是3个剪枝出来，如果字符串剩余长度>剩余最多剪枝长度，就剪枝
            if (residue * 3 < len - i) {
                continue;
            }
            // 判断字符里数字是否合法
            if (judgeIpSegment(s, begin, i)) {
                String currentIpSegment = s.substring(begin, i + 1);
                path.addLast(currentIpSegment);

                dfs(s, len, i + 1, residue - 1, path, res);
                // 剪枝
                path.removeLast();
            }
        }
    }

    private boolean judgeIpSegment(String s, int left, int right) {
        int len = right - left + 1;
        if (len > 1 && s.charAt(left) == '0') {
            return false;
        }

        int res = 0;
        while (left <= right) {
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }

        return res >= 0 && res <= 255;
    }


}
