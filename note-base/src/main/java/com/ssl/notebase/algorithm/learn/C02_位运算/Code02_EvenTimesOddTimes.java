package com.ssl.notebase.algorithm.learn.C02_位运算;

public class Code02_EvenTimesOddTimes {

    /**
     * arr中，只有一种数，出现奇数次；其余数出现偶数次
     */
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    /**
     * arr中，有两种数(两个数不相同)，出现奇数次
     */
    public static void printOddTimesNum2(int[] arr) {
        // 1.干掉所有偶数，得到a^b
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // a 和 b是两种数
        // eor != 0
        // eor最右侧的1，提取出来
        // eor :     00110010110111000
        // rightOne :00000000000001000
        // 2.取最右侧的1
        int rightOne = eor & (~eor + 1); // 提取出最右的1

        // 3.将这个1区分arr所有数
        int a = 0; // eor'
        for (int i = 0; i < arr.length; i++) {
            //  arr[1] =  111100011110000
            // rightOne=  000000000010000
            if ((arr[i] & rightOne) != 0) {
                a ^= arr[i];
            }
        }
        // b = eor ^ a = (a^b) ^a
        System.out.println(a + " " + (eor ^ a));
    }


    /**
     * N中1的次数
     */
    public static int bit1counts(int N) {
        int count = 0;
        while (N != 0) {
            //   011011010000
            //  如何能得到最右边的1
            //   000000010000
            // 把最右侧的1提取出来 = a & (-a+1) = a & (-a)
            int rightOne = N & ((~N) + 1);
            count++;
            N ^= rightOne;
            // N -= rightOne
        }
        return count;
    }


    public static void main(String[] args) {
        int[] arr1 = {3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1};
        printOddTimesNum1(arr1);

        int[] arr2 = {4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2};
        printOddTimesNum2(arr2);

    }

}
