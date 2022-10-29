package com.ssl.notebase.algorithm.leetcode.编号刷题.LC146_LRU缓存;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: SongShengLin
 * @Date: 2022/06/16 10:17 AM
 * @Describe:
 */
public class LRUCache {

    static class Node {
        int key;
        int value;

        Node pre;
        Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * size：链表初试化长度
     * 实际长度：mao.size
     */
    private int size;
    private Node head;
    private Node tail;
    private Map<Integer, Node> map;

    /**
     * 最近最少使用：
     * put/get都将使用过的元素放在链表末尾
     * 于是链表头部永远都是待删除的元素
     */
    public LRUCache(int capacity) {
        map = new HashMap<>();
        size = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        moveToTail(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            moveToTail(node);
            return;
        }
        if (map.size() == size) {
            Node tailNode = head.next;
            deleteNode(tailNode);
            map.remove(tailNode.key);
        }

        Node node = new Node(key, value);
        insertToTail(node);
        map.put(key, node);

    }

    private void moveToTail(Node node) {
        if (node == null) {
            return;
        }
        deleteNode(node);
        insertToTail(node);
    }

    private void deleteNode(Node node) {
        if (node == null) {
            return;
        }
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void insertToTail(Node node) {
        if (node == null) {
            return;
        }
        tail.pre.next = node;
        node.pre = tail.pre;
        node.next = tail;
        tail.pre = node;
    }

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1);// 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4
    }


}
