package com.myAlgorithms.shousi;

public class divide {
     // 1346278
    // 123 4 876
    public void solver(int[] nums, int p) {

    }

    public void sort(int[] nums, int l, int r) {
        int nextP = getPivot(nums, l, r);
        sort(nums, nextP, r);
        sort(nums, l, nextP - 1);
    }

    public int getPivot(int[] nums, int l, int r) {
        int p = l;
        l += 1;
        while (l <= r) {
            while (l <= r && nums[p] < nums[l]) {
                l ++;
            }
            while (r <= l )
        }
    }
}
