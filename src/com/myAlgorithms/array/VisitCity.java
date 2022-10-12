package com.myAlgorithms.array;

public class VisitCity {
    public int[] getMinCost(int[] aCost, int[] bCost, int c) {
        int[] dpA = new int[aCost.length + 1];
        int[] dpB = new int[bCost.length + 1];
        int[] res = new int[aCost.length + 1];

        for (int i = 1; i < aCost.length + 1; i ++) {
            dpA[i] = Math.min(dpA[i - 1] + aCost[i - 1], dpB[i - 1] + c + aCost[i - 1]);
            dpB[i] = Math.min(dpB[i - 1] + bCost[i - 1], dpA[i - 1] + c + bCost[i - 1]);
            res[i] = Math.min(dpA[i], dpB[i]);
        }
        return res;
    }
}
