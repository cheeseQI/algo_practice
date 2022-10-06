package com.myAlgorithms.array;

public class leastSwapSort {
    public int getMinSwap(int[] nums) {
        int count = 0;
        int right = nums.length - 1;
        int left = 0;
        while (left < right) {
            while (right >= 0 && nums[right] % 2 != 0) {
                right --;
            }
            while (left < right && nums[left] % 2 == 0) {
                left ++;
            }
            if (left >= right) {
                break;
            } else {
                // swap, but we do not need to do that
                count ++;
                right --;
                left ++;
            }
        }
        return count;
    }
}
