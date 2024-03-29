package com.ssl.note.algorithm.learn.C03_异或运算;

import java.util.HashMap;
import java.util.HashSet;

public class Code03_KM {

    // map:存每一个二进制位出现的元素次数
    public static HashMap<Integer, Integer> map = new HashMap<>();

    /**
     * 请保证arr中，只有一种数出现了K次，其他数都出现了M次
     * 一个数组中有一种数出现K次，其他数都出现了M次，
     * M > 1,  K < M
     * 找到，出现了K次的数，
     */
    public static int onlyKTimes(int[] arr, int k, int m) {
        // 1.初始化map，保存每个二进制出现的次数
        if (map.size() == 0) {
            mapCreater(map);
        }
        // 2.统计每个二进制位出现的总次数
        int[] t = new int[32];
        for (int num : arr) {
            while (num != 0) {
                // 一个数num，最右侧的1 = num & ~num+1 = num & -num
                int rightOne = num & (-num);
                t[map.get(rightOne)]++;
                // num每次都抹掉最右侧的1
                num ^= rightOne;
            }
        }
        // 3.查找出现k次的数
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            // 只有1个数出现了k次，其余数必须出现m次
            if (t[i] % m != 0) {
                if (t[i] % m == k) {
                    ans |= (1 << i);
                } else {
                    return -1;
                }
            }
        }
        // 4.如果这个数是0，判断这个数是否真的出现了k次
        if (ans == 0) {
            int count = 0;
            for (int num : arr) {
                if (num == 0) {
                    count++;
                }
            }
            if (count != k) {
                return -1;
            }
        }
        return ans;
    }

    public static void mapCreater(HashMap<Integer, Integer> map) {
        int value = 1;
        for (int i = 0; i < 32; i++) {
            // key=1，value=0
            // key=2，value=1
            // key=4，value=2
            // key=8，value=3
            map.put(value, i);
            value <<= 1;
        }
    }

    public static int[] randomArray(int maxKinds, int range, int k, int m) {
        int ktimeNum = randomNumber(range);
        // 真命天子出现的次数
        int times = Math.random() < 0.5 ? k : ((int) (Math.random() * (m - 1)) + 1);
        // 2
        int numKinds = (int) (Math.random() * maxKinds) + 2;
        // k * 1 + (numKinds - 1) * m
        int[] arr = new int[times + (numKinds - 1) * m];
        int index = 0;
        for (; index < times; index++) {
            arr[index] = ktimeNum;
        }
        numKinds--;
        HashSet<Integer> set = new HashSet<>();
        set.add(ktimeNum);
        while (numKinds != 0) {
            int curNum = 0;
            do {
                curNum = randomNumber(range);
            } while (set.contains(curNum));
            set.add(curNum);
            numKinds--;
            for (int i = 0; i < m; i++) {
                arr[index++] = curNum;
            }
        }
        // arr 填好了
        for (int i = 0; i < arr.length; i++) {
            // i 位置的数，我想随机和j位置的数做交换
            int j = (int) (Math.random() * arr.length);// 0 ~ N-1
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        return arr;
    }

    // [-range, +range]
    public static int randomNumber(int range) {
        return ((int) (Math.random() * range) + 1) - ((int) (Math.random() * range) + 1);
    }

    public static int test(int[] arr, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        for (int num : map.keySet()) {
            if (map.get(num) == k) {
                return num;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int kinds = 5;
        int range = 30;
        int testTime = 100000;
        int max = 9;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int a = (int) (Math.random() * max) + 1; // a 1 ~ 9
            int b = (int) (Math.random() * max) + 1; // b 1 ~ 9
            int k = Math.min(a, b);
            int m = Math.max(a, b);
            // k < m
            if (k == m) {
                m++;
            }
            int[] arr = randomArray(kinds, range, k, m);
            int ans1 = test(arr, k, m);
            int ans2 = onlyKTimes(arr, k, m);
            if (ans1 != ans2) {
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("出错了！");
            }
        }
        System.out.println("测试结束");

    }

}
