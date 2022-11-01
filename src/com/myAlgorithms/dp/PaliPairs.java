package com.myAlgorithms.dp;

import java.util.HashMap;
import java.util.Map;

public class PaliPairs {
    // abc abcd bc adc  -> abccba is valid pali
    public int checkValid(String ss[]) {
        // count whether odd occurred chars are even (abc cba there is 0 char occurs odd, 0 is even so exist)
        // bit mask; xor
        int res = 0;
        // two sum way to use memo, record freq of bitmask
        Map<Integer, Integer> freq = new HashMap();
        for (String s: ss) {
            int bitmask = 0; // bitmask / serialize the occurrence of char, we only care odd
            for (char c: s.toCharArray()) {
                bitmask ^= 1 << (c - 'a');
            }
            //出现完全相同的组合，那一定有办法形成回文（even chars），所以每个相同组合都是一种组合可能
            res += freq.getOrDefault(bitmask, 0);
            // 已知当前组合的情况下，假如另一个组合正好比这个组合多一个字母，不管是什么，这都能保证形成回文（one odd + all even chars）
            for (int i = 0; i < 26; i ++) {
                res += freq.getOrDefault(bitmask ^ (1 << i), 0);
            }
            freq.put(bitmask, freq.getOrDefault(bitmask, 0) + 1);
        }

        return res;
    }
}
