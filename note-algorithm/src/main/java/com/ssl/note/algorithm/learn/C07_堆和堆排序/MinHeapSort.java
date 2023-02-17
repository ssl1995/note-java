package com.ssl.note.algorithm.learn.C07_堆和堆排序;

import java.util.Arrays;

/**
 * @Author: SongShengLin
 * @Date: 2023/02/03 10:08
 * @Describe:
 */
public class MinHeapSort {

    // 从小到大排序
    public static void heapSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        // 1.大根堆，堆顶保持最大值
        for (int parent = (arr.length - 2) / 2; parent >= 0; parent--) {
            heapify(arr, parent, arr.length);
        }
        // 2.将堆顶最大值依次放回数组末尾 = 从小到大排序
        for (int i = arr.length - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }
    }


    private static void heapify(int[] arr, int parent, int n) {
        while (2 * parent + 1 < n) {
            int left = 2 * parent + 1;
            if (left + 1 < n && arr[left + 1] > arr[left]) {
                left++;
            }
            if (arr[parent] >= arr[left]) {
                break;
            }
            swap(arr, parent, left);
            parent = left;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 6, 6};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
