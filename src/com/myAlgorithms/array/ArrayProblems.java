package com.myAlgorithms.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ArrayProblems {
}

class TopK{
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // map：前面出现的所有前缀和 -> 该前缀和出现的次数
        HashMap<Integer, Integer>
                preSum = new HashMap<>();
        preSum.put(0, 1);

        int res = 0, sum0_i = 0;
        for (int i = 0; i < n; i++) {
            sum0_i += nums[i]; //always count until end
            int sum0_j = sum0_i - k; //the num we need
            // 如果前面有这个前缀和，则直接更新答案
            if (preSum.containsKey(sum0_j))
                res += preSum.get(sum0_j);
            // 把前缀和 nums[0..i] 加入并记录出现次数
            preSum.put(sum0_i,
                    preSum.getOrDefault(sum0_i, 0) + 1);
        }
        return res;
    }
}


class NSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        //fourSum -> threeSum -> 2Sum
        Arrays.sort(nums);
        List<List<Integer>> ans = nSum(nums, 0, nums.length - 1, target, 4);
        return ans;
    }

    List<List<Integer>> nSum(int[] nums, int begin, int end, int currTarget, int n) {
        List<List<Integer>> ans = new LinkedList<>();
        if (n == 2) { //two sum: base case
            int left = begin, right = end;

            while (left < right) {
                if (nums[left] + nums[right] > currTarget) {
                    right --;
                    while(right > left && nums[right] == nums[right + 1]) right --;
                } else if (nums[left] + nums[right] < currTarget){
                    left ++;
                    while (left < end && nums[left] == nums[left - 1]) left ++;
                } else {
                    List<Integer> currAns = new LinkedList<>();
                    currAns.add(nums[left]);
                    currAns.add(nums[right]);
                    ans.add(currAns);
                    right --;
                    while(right > left && nums[right] == nums[right + 1]) right --;
                    left ++;
                    while (left < end && nums[left] == nums[left - 1]) left ++;
                }
            }
            return ans;
        }

        while (begin < end) {
            //while (begin < end && nums[begin] == nums[begin + 1]) begin ++;
            List<List<Integer>> tempAnsForLowerLevelSum;
            tempAnsForLowerLevelSum = nSum(nums, begin + 1, end, currTarget - nums[begin], n - 1);
            for (List<Integer> l: tempAnsForLowerLevelSum) {
                l.add(nums[begin]);
                ans.add(l);
            }
            begin ++;
            while (begin < end && nums[begin] == nums[begin - 1]) begin ++;
        }
        return ans;
    }

}


class AirplanBooking {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n]; //diff[0] = 0
        for (int[] booking: bookings) {
            int start = booking[0] - 1; // 1 ~ n  -> 0 ~ n-1
            diff[start] += booking[2];
            int end = booking[1]; //1~n
            if (end < n) { //if end == n, no need to minus, since it is the end
                diff[end] -= booking[2];
            }
        }

        for (int i = 1; i < n; i++) { //recover the original array;
            diff[i] = diff[i - 1] + diff[i];
        }
        return diff;
    }
}