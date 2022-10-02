package com.myAlgorithms.array;

import java.util.HashMap;
import java.util.Map;

public class algo {
    // string s (low character) -> first only unique (value, idx)
    // "aabbcded" -> ('c', 4)
    // map <char x, freq>
    public void findFirstChararcter(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c: s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i ++) {
            if (freq.get(s.charAt(i)) == 1) {
                System.out.println(s.charAt(i));
                System.out.println(i);
                return;
            }
        }
    }
}
