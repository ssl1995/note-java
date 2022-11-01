package com.ssl.note.algorithm.leetcode.编号刷题.LC141_环形链表I;

import com.ssl.note.algorithm.leetcode.utils.ListNode;

public class Solution {
    /**
     * 判断链表是否有环，返回值是布尔值
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }
        // 初始化：快指针在next.next,慢指针在next上
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (fast != slow) {
            if (fast == null || fast.next == null || fast.next.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }

}
