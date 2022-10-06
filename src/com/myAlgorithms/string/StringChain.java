package com.myAlgorithms.string;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringChain {
    // ["an","a","and","bear"], remove one for step
    // return longest = 3 :  and -> an -> a
    public int getLongestChainLength(String[] s) {
        int res = 0;
        // construct dp/memo from shortest to longest
        // str, length of longest
        Map<String, Integer> memo = new HashMap<>();
        Arrays.sort(s);
        for (String curr: s) {
            int max = 0;
            for (int i = 0; i < curr.length(); i ++) {
                String removedS = curr.substring(0, i) + curr.substring(i + 1);
                max =  Math.max(max, 1 + memo.getOrDefault(removedS, 0));
                memo.put(curr, max);
            }
            res = Math.max(res, max);
        }

        return res;
    }

}
