package com.myAlgorithms.array;

import java.util.Deque;
import java.util.LinkedList;

public class clearAndSumAdjNums {
    // 假如升级版 不是数组而是字符串 277有个7所以要求和变成 214， 就可以这么做：stack存character，求和也是一个char一个char进去！如果数字本身有要先处理
    public int[] clear(int[] nums) {
        // [9,3,3,3,9,7] -> [27,7]
        Deque<Integer> stack = new LinkedList<>();
        stack.push(nums[0]);
        int j = 1;
        while (j < nums.length) {
            if (!stack.isEmpty() && nums[j] == stack.peek()) {
                int res = stack.peek();
                while (j < nums.length && nums[j] == stack.peek()) {
                    res += stack.peek();
                    j ++;
                }
                stack.pop();
                if (j > 0) {
                    j --; //退格 因为可能求和与之前的栈重复
                    nums[j] = res; // 为下一轮比较做准备
                } else {
                    stack.push(res);
                }
            } else {
                stack.push(nums[j]);
                j ++;
            }
        }

        int[] res = new int[stack.size()];
        int i = stack.size() - 1;
        while (!stack.isEmpty()) {
            res[i] = stack.pop();
            i --;
        }
        return res;
    }

    private void checkBefore() {

    }

    private void merge(Deque<Integer> stack) {

    }
}
