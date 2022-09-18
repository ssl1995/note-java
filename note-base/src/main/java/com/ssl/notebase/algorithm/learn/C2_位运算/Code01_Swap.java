package com.ssl.notebase.algorithm.learn.C2_位运算;

public class Code01_Swap {

    public static void main(String[] args) {

        int a = 1;
        int b = 2;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);

        System.out.println("-----");

        int[] arr = {1, 2, 3};
        // i==j,会把结果为0
        swap1(arr, 0, 0);

        System.out.println(arr[0]);
        System.out.println(arr[1]);

        System.out.println("-----");
        int[] arr1 = {1, 2, 3};
        // i==j,直接return
        swap2(arr1, 0, 0);

        System.out.println(arr1[0]);
        System.out.println(arr1[1]);
    }

    public static void swap1(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


    public static void swap2(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


}
