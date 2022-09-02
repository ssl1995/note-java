package com.ssl.notebase.leetcode.力扣.精选Top面试.LC155_最小栈;

import java.util.LinkedList;

/**
 * @author SongShengLin
 * @date 2022/1/9 12:08 PM
 * @description
 */
public class MinStack {
    /**
     * 栈1：数据栈，正常压入弹出
     */
    private LinkedList<Integer> stack1;

    /**
     * 栈2：辅助栈，栈顶保持栈1中的最小值
     */
    private LinkedList<Integer> stack2;

    public MinStack() {
        stack1 = new LinkedList<>();
        stack2 = new LinkedList<>();
    }

    public void push(int x) {
        // 栈2压入：1.x大就压入min；2.x小就压入x
        if (stack2.isEmpty() || x <= getMin()) {
            stack2.push(x);
        }
        if (x > getMin()) {
            stack2.push(getMin());
        }
        // 栈1每次正常进栈
        stack1.push(x);
    }

    public void pop() {
        if (stack1.isEmpty()) {
            throw new RuntimeException("MinStack is empty");
        }
        // 出栈是两个辅助栈都要出
        stack1.pop();
        stack2.pop();
    }

    public int top() {
        if (stack1.isEmpty()) {
            throw new RuntimeException("MinStack is empty");
        }
        return stack1.peek();
    }

    public int getMin() {
        if (stack2.isEmpty()) {
            throw new RuntimeException("MinStack is empty");
        }
        return stack2.peek();
    }
}
