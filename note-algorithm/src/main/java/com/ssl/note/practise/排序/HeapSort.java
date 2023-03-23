package com.ssl.note.practise.排序;

import java.util.Arrays;

/**
 * @author SongShengLin
 * @date 2023/3/3 13:23
 * @description
 */
public class HeapSort {

    /**
     * @param nums
     */
    public void heapSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        // test笔记
        // test笔记2
        for (int i = (nums.length - 2) / 2; i >= 0; i--) {
            heapify(nums, i, nums.length);
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            swap(nums, 0, i);
            heapify(nums, 0, i);
        }
    }

    private void heapify(int[] nums, int parent, int n) {
        while (2 * parent + 1 < n) {
            int left = 2 * parent + 1;

            left = left + 1 < n && nums[left] < nums[left + 1] ? left + 1 : left;

            if (nums[parent] >= nums[left]) {
                break;
            }

            swap(nums, parent, left);
            parent = left;
        }
    }

    private void swap(int[] nums, int left, int right) {
        if (left == right) {
            return;
        }
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    public static void main(String[] args) {
        HeapSort solution = new HeapSort();
        int[] arr = {3, 4, 1, 2};
        solution.heapSort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
