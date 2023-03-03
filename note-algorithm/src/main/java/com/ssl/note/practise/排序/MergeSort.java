package com.ssl.note.practise.排序;

import java.util.Arrays;

/**
 * @author SongShengLin
 * @date 2023/3/3 11:40
 * @description
 */
public class MergeSort {

    public void mergeSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        mergeSort(nums, 0, nums.length - 1);
    }


    private void mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[nums.length];
        System.arraycopy(nums, left, temp, left, right - left + 1);

        int p = left;
        // q从mid+1开始
        int q = mid + 1;
        // 左闭右闭
        for (int i = left; i <= right; i++) {
            if (p > mid) {
                nums[i] = temp[q++];
            } else if (q > right) {
                nums[i] = temp[p++];
            } else if (temp[p] < temp[q]) {
                nums[i] = temp[p++];
            } else {
                nums[i] = temp[q++];
            }
        }
    }

    public static void main(String[] args) {
        MergeSort solution = new MergeSort();
        int[] arr = {3, 4, 1, 2};
        solution.mergeSort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
