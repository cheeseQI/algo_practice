package com.myAlgorithms.dp;

import java.util.PriorityQueue;

public class MostTransaction {
    // [3 2 -5 -6 -1 4] -> keep balance non-negative and can skip -> choose idx = 0, 1, 2, 5 so balance =[3, 5, 0, 4]
    public int getMost(int[] trans) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int balance = 0;
        int count = 0;
        for (int i = 0; i < trans.length; i ++) {
            balance += trans[i];
            count ++;
            if (trans[i] < 0) {
                pq.add(trans[i]);
            }
            while (!pq.isEmpty() && balance < 0) {
                balance -= pq.poll();
                count --;
            }
            if (balance < 0) {
                balance = 0;
                count = 0;
            }
        }
        return count;
    }
}
