package com.myAlgorithms.array;

import java.util.Deque;
import java.util.LinkedList;

public class clearAdjNums {
    // A = 0, B = 1; A开始进行相邻数字消除，一轮只能消除一次，AB交替一直到无法消除为止的那个人输，返回谁赢。
    // 1,2,3,3,2,1,4,4,5,6 -> A, B, A, B 分别按顺序消除 -> 最后剩下56给A没办法消除，返回0
    public int getWinner(int[] nums) {
        int loser = 0;
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < nums.length; i ++) {
            while (!stack.isEmpty() && i < nums.length && nums[i] == stack.peek()) {
                stack.pop();
                i ++;
                loser = 1 - loser;
            }
            if (i >= nums.length) {
                break;
            }
            stack.push(nums[i]);
        }

        return loser;
    }
}
