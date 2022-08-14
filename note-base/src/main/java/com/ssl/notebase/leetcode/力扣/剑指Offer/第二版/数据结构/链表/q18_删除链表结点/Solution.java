package com.ssl.notebase.leetcode.力扣.剑指Offer.第二版.数据结构.链表.q18_删除链表结点;


import com.ssl.notebase.leetcode.力扣.utils.ListNode;

public class Solution {
    /**
     * 删除链表中指定结点值
     */
    public ListNode deleteNode(ListNode head, int val) {
        // 空链表
        if (head == null) {
            return null;
        }
        // 如果待删除结点是头结点且链表不止一个结点
        if (head.val == val) {
            return head.next;
        }
        ListNode cur = head;
        while (cur.next != null && cur.next.val != val) {
            cur = cur.next;
        }
        if (cur.next != null) {
            cur.next = cur.next.next;
        }
        return head;
    }
}
