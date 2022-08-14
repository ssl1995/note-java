package com.ssl.notebase.leetcode.力扣.剑指Offer.第三版_专项突击版.第4章_链表.q24_反转链表;

import com.ssl.notebase.leetcode.力扣.utils.ListNode;

/**
 * @author SongShengLin
 * @date 2021/9/23 9:20 上午
 * @description 反转单向链表
 */
public class Solution {
    public ListNode reverseList(ListNode head) {
        // pre = 待反转结点的前一个结点，最后返回它
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
