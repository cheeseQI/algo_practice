package com.myAlgorithms.shousi;

import java.util.Random;

public class quickSort {
    public int[] sort(int[] nums, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(nums, left, right);
            sort(nums, left, partitionIndex - 1);
            sort(nums, partitionIndex + 1, right);
        }
        return nums;
    }

    private int partition(int[] arr, int left, int right) {
        int randomIndex = new Random().nextInt(right - left + 1) + left;
        swap(arr, left, randomIndex);
        int lt = left;
        int pivot = arr[lt];
        //  lt lt lt lt P gt gt gt
        for (int i = left + 1; i <= right; i ++) {
            if (arr[i] < pivot) {
                lt ++;
                swap(arr, lt, i);
            }
        }
        swap(arr, lt, left); // swap pivot with leftmost lt
        return lt;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
