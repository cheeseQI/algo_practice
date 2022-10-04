package com.myAlgorithms.array;

import java.util.*;

public class jumpSame {
    // 第四题是给一维array numbers和int diff，从numbers的任意一格起跳，跳至相同value的其他格子，每次跨度<=diff，求最多可跳的次数。
    public int getMax(int[] nums, int diff) {
        int res = 0;
        Map<Integer, List<Integer>> steps = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            if (steps.containsKey(nums[i])) {
                List<Integer> currStepList = steps.get(nums[i]);
                if (i - currStepList.get(currStepList.size() - 1) <= diff) {
                    currStepList.add(i);
                    steps.put(nums[i], currStepList);
                }
            } else {
                List<Integer> l = new ArrayList<>();
                l.add(i);
                steps.put(nums[i], l);
            }
        }
        for (Map.Entry<Integer, List<Integer>> e: steps.entrySet()) {
            res = Math.max(res, e.getValue().size());
        }
        return res;
    }
}
