package com.ssl.note.algorithm.leetcode.编号刷题.LC25_k个一组反转链表;

import com.ssl.note.algorithm.leetcode.utils.ListNode;

/**
 * @Author: SongShengLin
 * @Date: 2022/12/27 10:28
 * @Describe:
 */
public class Solution {
    /**
     * k个一组反转链表
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k < 0) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        // 翻转外围的指针
        ListNode pre = dummy;
        ListNode next = null;
        // 真正反转的内部指针
        ListNode end = dummy;
        ListNode start = null;

        while (end.next != null) {
            // 不够k个，保持原样
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            // 说明整个链表长度不够k个，直接保持原样返回
            if (end == null) {
                break;
            }
            // 确定外围指针、反转内部指针
            start = pre.next;
            next = end.next;
            // 翻转开始
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            // 重置pre和reverseEnd
            end = start;
            pre = end;
        }

        return dummy.next;
    }


    /**
     * 反转单个链表
     */
    private ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        ListNode head = getTestHead();
        int k = 2;
        System.out.print("原始链表:");
        printNode(head);
        System.out.print("反转k个链表后:");
        ListNode res = solution.reverseKGroup(head, k);
        printNode(res);
    }

    private static ListNode getTestHead() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        return node1;
    }

    private static void printNode(ListNode node) {
        while (node != null) {
            if (node.next != null) {
                System.out.print(node.val + "->");
            } else {
                System.out.println(node.val);
            }
            node = node.next;
        }
    }
}
