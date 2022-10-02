package com.myAlgorithms.dp;

import java.util.Arrays;

public class DPProblems {
}

class JumpGame{
    //永远选择能够覆盖最远范围的idx, 走一个完整的跳跃区间就加一次次数，然后以当前farthes做下一个end
    public int jump(int[] nums) {
        if (nums.length == 1) return 0;

        int count = 0;
        int farthest = 0;
        int end = 0;

        for (int i = 0; i < nums.length; i ++) {
            farthest = Math.max(farthest, nums[i] + i);
            if (farthest >= nums.length - 1) break;

            if (i == end) {
                end = farthest; //next jump farthest position
                count ++;
            }
        }
        return count + 1; //最后一跳得加上
    }
}


class KMP {
    int[][] dp;
    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        if (m < n) return -1;
        if (n == 0) return 0;

        //dp[i][c] = ns; -> when current character is c, and state is i, the next state is ns;
        dp = new int[n][26]; // n state,26 character
        buildKMP(needle);
        //search using made KMP table
        return findIndexOf(haystack);
    }

    void buildKMP(String needle) {
        dp[0][needle.charAt(0) - 'a'] = 1; //base case: 0 -> 1 need first character of needle
        int cloneLength = 0; //a->b->a->b->c have two(a->b), second one's cloneSubState is first one, so
        //第二个a->b结束的时候如果下一个状态不符合条件（不是c）， 那就回退到第一个a->b的末尾
        for (int i = 1; i < needle.length(); i ++) { //start with state 1
            for (int c = 0; c < 26; c ++) {
                if (c == needle.charAt(i) - 'a') {
                    dp[i][c] = i + 1;
                } else {
                    dp[i][c] = dp[cloneLength][c];
                }
            }
            cloneLength = dp[cloneLength][needle.charAt(i) - 'a'];
        }
    }

    int findIndexOf(String haystack) {
        int state = 0;
        for (int i = 0; i < haystack.length(); i ++) {
            state = dp[state][haystack.charAt(i) - 'a'];
            if (state == dp.length) return (i - state + 1); //return index
        }
        return -1;
    }
}

class FindRemainCoins {
    public int coinChange(int[] coins, int amount) {
        //base : 0 any coins->0 amount; infinite 0 conis-> any amount>=1
        //dp[i][j] = m : with first i th coins, the target amount is j, min nums of coin is m;
        //dp[i][j] = min(dp[i - 1][j], dp[i][j - coins of i]); corner: j > coins of i

        int n =  coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        /*for (int i = 1; i < n; i ++) {
            dp[i][0] = 0;
        }*/
        for (int j = 1; j <= amount; j ++) {
            dp[0][j] = amount + 1;
        }

        for (int i = 1; i <= n; i ++) {
            for (int j = 1; j <= amount; j ++) {
                if (j >= coins[i - 1]) {
                    dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coins[i - 1]]); // -1 !!!
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][amount] == amount + 1 ? -1 : dp[n][amount];
    }
}


class PalidromeSubseq {
    public int longestPalindromeSubseq(String s) {
        //dp[i][j] = m, start with s[i], end with s[j], max subseq length = m;
        //dp[i][j] = 2 + dp[i + 1][j - 1] || max(dp[i][j - 1], dp[i + 1][j])
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int k = 0; k < n ; k ++) dp[k][k] = 1;

        for (int i = n - 1; i >= 0; i --) {
            for (int j = i + 1; j < n; j ++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][n - 1];
    }
}


class LongestIncreasedSubseq {
    public int lengthOfLIS(int[] nums) {
        //dp[i]  = l; subseq end with nums[i] , which length would be l;
        //dp[i] = Math.max(dp[all js if nums[j] < nums[i]] + 1, dp[i])

        int[] dp = new int[nums.length];
        //Arrays.fill(dp, 0);
        int max = 0;
        for (int i = 0; i < nums.length; i ++) {
            for (int j = 0; j < i; j ++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    //if max-> record j as maxIdx, add into list
                }
                max = max > dp[i] ? max : dp[i];//这行其实应该在外循环更新
            }
        }
        return max + 1;
    }

    //贪心 + 二分
    class Solution2 {
        public int lengthOfLIS(int[] nums) {
            int[] ans = new int[nums.length];
            int max = 0;
            int end = 0; //start always = 0;
            ans[end] = nums[end];
            for (int i = 1; i < nums.length; i ++) {
                if (nums[i] > ans[end]) {
                    end ++;
                    ans[end] = nums[i];
                } else { //二分
                    int left = 0;
                    int right = end;
                    while (left < right) { //二分一定要写三种情况都得写清楚条件，要不然容易出错不好检查
                        int mid = left + (right - left) / 2;
                        if (ans[mid] > nums[i]) {
                            right  = mid;
                        } else if (ans[mid] < nums[i]){
                            left = mid + 1;
                        } else {
                            left = mid; //used for breaking保证替换的是相等的这个数字
                            break;
                        }
                    }
                    //left == right => pos to replace
                    ans[left] = nums[i]; //这里很反常，其实输出的是个错误的数组，但是我们保持了长度的正确性（替换不改变最大长度）
                }
            }
            return end + 1; //长度比索引大一
        }
    }



    public int findNumberOfLIS(int[] nums) {
        int n = nums.length,  maxLen = 0, ans = 0;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(cnt, 1);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    //其实就是根据count对策不一样，把这个拆开； dp[i] = Math.max(dp[j] + 1, dp[i]);
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j]; //之前j结尾的全变成i结尾的plans了；但是本身j结尾还是不变的plans，相当于不选择i
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j]; //无论是直接i结尾还是j之后接一个i结尾都有相同plan，那意味着加起来
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        for (int i = 0; i < cnt.length; i ++) {
            if (dp[i] == maxLen) {
                ans += cnt[i]; //可能有以不同数字结尾的plans，都有maxlen，所以其实这步还得叠加
            }
        }
        return ans;
    }
}

