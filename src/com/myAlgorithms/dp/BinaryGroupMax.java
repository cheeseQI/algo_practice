package com.myAlgorithms.dp;

public class BinaryGroupMax {
    public int getMax(int min, int max, int zeros, int ones) {
        int res = 0;
        int[] dp = new int[max + 1];
        dp[0] = 1; // nothing is also a plan
        for (int i = 1; i <= max; i ++) {
            if (i >= ones) {
                dp[i] += dp[i - ones];
            }
            if (i >= zeros) {
                dp[i] += dp[i - zeros];
            }
        }
        for (int i = min; i <= max; i ++) {
            res += dp[i];
        }
        return res;
    }
}
