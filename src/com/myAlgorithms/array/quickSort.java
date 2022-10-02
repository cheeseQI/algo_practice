package com.myAlgorithms.array;

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
        int pivot = left;
        int l = 1 + left;
        int r = right;
        while (l <= r) {
            while (l < right && arr[l] <= arr[pivot]) {
                l ++;
            }
            while (r > left && arr[r] > arr[pivot]) {
                r --;
            }
            if (l >= r) {
                break;
            }
            swap(arr, l, r);
        }
        swap(arr, pivot, r);
        return r;
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
