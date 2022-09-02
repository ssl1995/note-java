package com.ssl.notebase.leetcode.力扣.精选Top面试.LC20_有效的括号;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author SongShengLin
 * @date 2022/1/20 11:29 PM
 * @description
 */
public class Solution {

    /**
     * 有效的括号
     * 输入：s = "{[]}"
     * 输出：true
     */
    public boolean isValid(String s) {
        if (s == null || s.length() <= 1) {
            return false;
        }
        // 创建左右括号对应的map
        Map<Character, Character> map = new HashMap<>(3) {{
            put('{', '}');
            put('[', ']');
            put('(', ')');
        }};
        LinkedList<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            // c=左括号，就入栈
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                // c=右括号，就比对栈顶是否匹配
                if (stack.isEmpty() || map.get(stack.peek()) != c) {
                    return false;
                }
                stack.pop();
            }
        }
        // 结果：判断栈是否为空
        return stack.isEmpty();
    }
}
