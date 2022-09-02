package com.ssl.notebase.learn.哈希;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class HashMap<K, V> extends AbstractMap<K, V>
        implements Map<K, V>, Cloneable, Serializable {
    /**
     * 1.允许 null 值，不同于 HashTable ，是线程不安全的；
     * 2.load factor（影响因子） 默认值是 0.75，
     * 是均衡了时间和空间损耗算出来的值，较高的值会减少空间开销（扩容减少，数组大小增长速度变慢），
     * 但增加了查找成本（hash 冲突增加，链表长度变长），不扩容的条件：数组容量 > 需要的数组大小 /load factor；
     * 3.如果有很多数据需要储存到 HashMap 中，建议 HashMap 的容量一开始就设置成足够的大小，这样可以防止在其过程中不断的扩容，影响性能；
     * 4.HashMap 是非线程安全的，我们可以自己在外部加锁，或者通过 Collections#synchronizedMap 来实现线程安全，
     * Collections#synchronizedMap 的实现是在每个方法上加上了 synchronized 锁；
     * 5.在迭代过程中，如果 HashMap 的结构被修改，会快速失败。
     */
    private static final long serialVersionUID = 362498820763181265L;

    // 默认初始化容量：2^4=16
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    // 最大容量
    static final int MAXIMUM_CAPACITY = 1 << 30;
    // 状态因子
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    // 桶上的链表长>=8时，链表转化成红黑树
    static final int TREEIFY_THRESHOLD = 8;
    // 桶上的链表长度<6时，链表转化成红黑树
    static final int UNTREEIFY_THRESHOLD = 6;
    // 数组容量>64,链表也会转换为红黑树
    static final int MIN_TREEIFY_CAPACITY = 64;

    // 记录迭代过程中 HashMap 结构是否发生变化，如果有变化，迭代时会 fail-fast
    transient int modCount;

    //HashMap 的实际大小，可能不准(因为当你拿到这个值的时候，可能又发生了变化)
    transient int size;

    // 扩容的门槛，有两种情况
    // 如果初始化时，给定数组大小的话，通过 tableSizeFor 方法计算，
    // 数组大小永远接近于 2 的幂次方，比如你给定初始化大小 19，实际上初始化大小为 32，为 2 的 5 次方。
    // 如果是通过 resize 方法进行扩容，大小 = 数组容量 * 0.75
    int threshold;

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}

