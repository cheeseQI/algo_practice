package com.myAlgorithms.string;

import java.util.Arrays;

public class permutationOfNum {
    // "1 45 72 55 115" "46 1 73 54 115"
    public boolean checkPermutation(String a, String b) {
        String[] arrayAS = a.split(" ");
        String[] arrayBS = b.split(" ");
        if (arrayAS.length != arrayBS.length) {
            return false;
        }
        int[] arrayA = new int[arrayAS.length];
        int[] arrayB = new int[arrayBS.length];
        for (int i = 0; i < arrayA.length; i ++) {
            arrayA[i] = Integer.parseInt(arrayAS[i]);
            arrayB[i] = Integer.parseInt(arrayBS[i]);
        }
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        for (int i = 0; i < arrayA.length; i ++) {
            int valA = arrayA[i];
            int valB = arrayB[i];
            if (valA != valB && valA + 1 != valB) {
                return false;
            }
        }
        return true;
    }
}
