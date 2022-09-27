package com.ssl.notebase.algorithm.leetcode.剑指Offer.第二版.数据结构.链表.q23_链表中环的入口结点;

import com.ssl.notebase.algorithm.leetcode.utils.ListNode;

/**
 * @author SongShengLin
 * @date 2021/12/15 9:17 AM
 * @description
 */
public class Solution {
    /**
     * 判断链表是否有环
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }
        // 初始化：快指针在next.next,慢指针在next上
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (fast != slow) {
            if (fast.next == null || fast.next.next == null) {
                return false;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }
}
