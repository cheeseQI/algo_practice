package com.myAlgorithms.array;

public class longestSubArrayK {
    // For example arr[] = {1, 2, 1, 0, 1, -8, -9, 0} and K=4, then at index=4 sum=5 but negative sum from i+1 to n
    // is -17, so cumulative sum would be less than 4 so keep that i in sum.
    // sliding window can be enlarged as much as possible since there may be negSum in the following
    public int getLongest(int[] nums, int k) {
        int backSum = 0; // pre sum from backward
        // ns[i] -> sum which is after i
        int[] negSum = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i --) {
            negSum[i] = backSum;
            if (backSum + nums[i] >= 0) {
                backSum = 0;
            } else {
                backSum = backSum + nums[i];
            }
        }

        int l = 0;
        int r = 0;
        int sum = 0, res = 0;
        while (r < nums.length && l <= r) {
            sum += nums[r];
            while (l <= r && sum + negSum[r] > k) {
                sum -= nums[l];
                l ++;
            }
            res = Math.max(res, r - l + 1);
            r ++;
        }
        return res;
    }
}
