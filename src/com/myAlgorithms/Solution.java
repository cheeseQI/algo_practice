package com.myAlgorithms;
import java.util.*;

public class Solution {
    /* 给定一个数组arr，返回arr的最长无重复元素子数组的长度，无重复指的是所有数字都不相同。
    子数组是连续的，比如【1,3,5,7,9】的子数据有【1,3】，【3,5,7】等等，但是【1,3,7】不是子数组
     */
    public int getLongestSubarray(int[] arr) {
        int ans = 0;
        Set<Integer> memo = new HashSet<>();
        int left = 0;
        int right = 0;
        while (right < arr.length) {
            if (memo.contains(arr[right])) {
                memo.add(arr[right]);
                while (memo.contains(arr[right])) {
                    memo.remove(arr[left]);
                    left ++;
                }
            }
            memo.add(arr[right]);
            right ++;
            int length = right - left;
            ans = ans > length ? ans : length;
        }
        return ans;
    }
}
