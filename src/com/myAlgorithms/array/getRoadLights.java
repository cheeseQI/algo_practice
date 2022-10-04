package com.myAlgorithms.array;

public class getRoadLights {
    // O(M + N) N: length of row of nums, M: value of max light in nums
    public int[] getNums(int[][] nums, int[] targets) {
        int max = 0;
        for (int[] interval: nums) {
            max = Math.max(max, interval[1]);
        }
        int[] diff = new int[max + 1]; // 初始即为全都是0的差分数组，长度需要覆盖到最远路灯
        for (int[] interval: nums) {
            diff[interval[0]] += 1;
            diff[interval[1]] -= 1;
        }
        // diff to result;
        int[] res = new int[diff.length];
        res[0] = 0;
        for (int i = 1; i < diff.length; i ++) {
            res[i] = res[i - 1] + diff[i];
        }
        int[] ans = new int[targets.length];
        for (int i = 0; i < ans.length; i ++) {
            ans[i] = res[targets[i]];
        }
        return ans;
    }
}
