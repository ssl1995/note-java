package com.ssl.notebase.coding;

import com.ssl.notebase.leetcode.力扣.utils.ListNode;

import java.util.*;

/**
 * @author SongShengLin
 * @date 2022/8/14 18:28
 * @description
 */
public class Solution {

    /**
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 != null) {
            cur.next = list1;
        }
        if (list2 != null) {
            cur.next = list2;
        }
        return dummy.next;
    }
}
