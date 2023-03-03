package com.ssl.note.practise.排序;

import java.util.Arrays;
import java.util.Random;

/**
 * @author SongShengLin
 * @date 2023/3/3 12:57
 * @description
 */
public class QuickSort {

    public void quickSort(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = partition(nums, left, right);
        quickSort(nums, left, p);
        quickSort(nums, p + 1, right);
    }

    private int partition(int[] nums, int left, int right) {
        Random random = new Random();
        int p = left + random.nextInt(right - left + 1);
        swap(nums, left, p);

        int i = left + 1;
        int j = right;
        while (true) {
            if (i <= j && nums[i] < nums[left]) {
                i++;
            }
            if (i <= j && nums[j] > nums[left]) {
                j--;
            }
            if (i >= j) {
                break;
            }
            swap(nums, i++, j--);
        }
        swap(nums, left, j);

        return j;
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
        QuickSort solution = new QuickSort();
        int[] arr = {3, 4, 1, 2};
        solution.quickSort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
